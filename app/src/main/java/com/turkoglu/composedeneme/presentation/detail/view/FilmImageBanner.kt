package com.turkoglu.composedeneme.presentation.detail.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.turkoglu.composedeneme.data.local.Favorite
import com.turkoglu.composedeneme.presentation.detail.DetailScreenViewModel
import com.turkoglu.composedeneme.presentation.fav.FavViewModel
import com.turkoglu.composedeneme.presentation.ui.primaryDark
import kotlinx.coroutines.launch


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FilmImageBanner(
    rating: Float,
    viewModel: DetailScreenViewModel,
    navController: NavController,
    posterUrl: String,
    filmName: String,
    filmId: Int,
    releaseDate: String,
    viewModelFav: FavViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.state.value
    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = primaryDark,
        modifier = Modifier
            .height(420.dp),
        elevation = 2.dp
    ) {
        Column {
            Box {
                AsyncImage(
                    model = state.posterPath,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(370.dp)
                        .graphicsLayer {
                            alpha = 1f - 0.1f
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = "Movie Banner"
                )
                FilmNameAndRating(
                    filmName = state.title,
                    rating = rating
                )
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
    ) {
        CircularBackButtons(onClick = { navController.popBackStack() })
        FragmanButton {
            //youtube
        }
        CircularFavoriteButtons(
            isLiked = viewModelFav.isAFavorite(filmId) != null,
            onClick = { isFav ->
                coroutineScope.launch {

                    if (isFav) {
                        Toast.makeText(
                            context,
                            "Already added to your favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@launch
                    } else {
                        viewModelFav.insertFavorite(
                            Favorite(
                                favorite = true,
                                mediaId = filmId,
                                image = posterUrl,
                                title = filmName,
                                releaseDate = releaseDate,
                                rating = rating
                            )
                        )
                    }
                }

            }
        )
    }
}