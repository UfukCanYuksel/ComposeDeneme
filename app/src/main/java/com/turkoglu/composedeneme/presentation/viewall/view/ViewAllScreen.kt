package com.turkoglu.composedeneme.presentation.viewall.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.turkoglu.composedeneme.domain.model.Movie
import com.turkoglu.composedeneme.presentation.home.MovieListItem
import com.turkoglu.composedeneme.presentation.viewall.ViewAllScreenViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun ViewAllScreen (
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ViewAllScreenViewModel = hiltViewModel(),
    navigateToDetail: (Movie) -> Unit
){
    val warMovie = viewModel.state.value.collectAsLazyPagingItems()
    val nameState = viewModel.nameState.value
    Column {

        Text(text = "${nameState.movies}", color = Color.White, fontSize = 18.sp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(
                warMovie.itemSnapshotList.items,
                key = { _: Int, movie: Movie -> movie.id }) { index, movie ->
                MovieListItem(
                    modifier = modifier
                        .height(200.dp)
                        .width(180.dp),
                    movie = movie,
                    onMovieClick = {navigateToDetail(movie)}
                )
            }
        }
    }
}