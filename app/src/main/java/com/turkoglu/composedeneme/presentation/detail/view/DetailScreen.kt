package com.turkoglu.composedeneme.presentation.detail.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.turkoglu.composedeneme.presentation.detail.DetailScreenViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable()
fun DetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DetailScreenViewModel = hiltViewModel()

) {

    val state = viewModel.state.value
    val castState = viewModel.castState


    Column(modifier = modifier.fillMaxSize()) {

        Text(text = state.title)
        Spacer(modifier = modifier.padding(20.dp))
        Text(text = state.overview)


    }

    val scrollState = rememberLazyListState()

    Box {

        FilmInfo(
            scrollState = scrollState,
            overview = state.overview,
            releaseDate = state.releaseDate
        )
        FilmImageBanner(
            rating = state.voteAverage.toFloat(),
            viewModel = viewModel
        )
        Text(text = "Casts", color = Color.White, fontSize = 18.sp)

        CastItem( cast = castState.value, modifier =modifier )



    }


}