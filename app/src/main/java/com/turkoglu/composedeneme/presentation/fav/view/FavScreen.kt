package com.turkoglu.composedeneme.presentation.fav.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavController
import androidx.room.InvalidationTracker
import coil.compose.rememberImagePainter
import com.turkoglu.composedeneme.R
import com.turkoglu.composedeneme.data.local.Favorite
import com.turkoglu.composedeneme.data.remote.dto.Cast
import com.turkoglu.composedeneme.presentation.CenterText
import com.turkoglu.composedeneme.presentation.detail.view.VoteAverageRatingIndicator
import com.turkoglu.composedeneme.presentation.fav.FavViewModel
import com.turkoglu.composedeneme.presentation.ui.Transparent
import com.turkoglu.composedeneme.presentation.ui.primaryDark
import com.turkoglu.composedeneme.presentation.ui.primaryPink

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavScreen(

    navController: NavController,
    viewModel: FavViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val openDialog = remember { mutableStateOf(false) }
        val favoriteFilms = viewModel.favorites.value ?: emptyList()

        Row(modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Favorites",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )


            IconButton(onClick = {
                openDialog.value = true
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }

        LazyColumn {
            itemsIndexed(items = favoriteFilms, key = {_: Int ,favoriteFilm : Favorite -> favoriteFilm.mediaId
            }) { _,favorite ->

                val dismissState = rememberDismissState()

                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    viewModel.deleteOneFavorite(favorite)
                }
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = Dp(1f)),
                    directions = setOf(
                        DismissDirection.EndToStart
                    ),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                    },
                    background = {
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.Default -> primaryDark
                                else -> primaryPink
                            }, label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        val icon = Icons.Default.Delete

                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f,
                            label = ""
                        )

                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = Dp(20f)),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                icon,
                                contentDescription = "Delete Icon",
                                modifier = Modifier.scale(scale)
                            )
                        }
                    },
                    dismissContent = {

                        Card(
                            elevation = animateDpAsState(
                                if (dismissState.dismissDirection != null) 4.dp else 0.dp,
                                label = ""
                            ).value,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(230.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .clickable {
                                    navController.navigate(route = "Detail/${favoriteFilms}")
                                }
                        ) {
                            FilmItem(
                                filmItem = favorite,
                            )
                        }
                    }
                )
            }
        }



        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
        }


        if (openDialog.value) {
            AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Delete all favorites")
                },
                text = {
                    Text(text = "Are you want to delete all?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteAllFavorites()
                            openDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(primaryPink)
                    ) {
                        Text(text = "Yes", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(primaryPink)
                    ) {
                        Text(text = "No", color = Color.White)
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
        }

    }
}

@Composable
fun FilmItem(
    filmItem: Favorite,
) {
    Box {
        Image(
            painter = rememberImagePainter(
                data = filmItem.image,
                builder = {
                    placeholder(R.drawable.ic_launcher_background)
                    crossfade(true)
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Movie Banner"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.3f, Transparent),
                            Pair(1.5f, primaryDark)
                        )
                    )
                )
        )

        FilmDetails(
            title = filmItem.title,
            releaseDate = filmItem.releaseDate,
            rating = filmItem.rating
        )
    }
}

@Composable
fun FilmDetails(
    title: String,
    releaseDate: String,
    rating: Float
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = releaseDate,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }
            VoteAverageRatingIndicator(
                percentage = rating
            )
        }
    }
}