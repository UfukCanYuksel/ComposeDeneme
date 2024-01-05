package com.turkoglu.composedeneme.presentation.detail.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
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
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.turkoglu.composedeneme.presentation.detail.DetailScreenViewModel
import com.turkoglu.composedeneme.presentation.ui.primaryDark
import com.turkoglu.composedeneme.presentation.ui.primaryPink

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FilmImageBanner(
    rating: Float,
    viewModel: DetailScreenViewModel,
    navController: NavController

) {

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

                //bu box pembelik koyuyo fotoğrafa
                /*Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.3f, Transparent),
                                    Pair(2f, primaryPink)
                                )
                            )
                        )
                )
                 */
                // istersen kaldır istersen dursun


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
        CircularBackButtons(onClick = { navController.popBackStack()})
        FragmanButton{
            //youtube
        }
        CircularFavoriteButtons(isLiked = true){
            //favorilere ekleme

        }
    }
}