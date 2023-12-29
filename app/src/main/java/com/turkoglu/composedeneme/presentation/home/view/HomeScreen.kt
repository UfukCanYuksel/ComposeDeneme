package com.turkoglu.composedeneme.presentation.home.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.turkoglu.composedeneme.domain.model.Movie
import com.turkoglu.composedeneme.presentation.home.HomeViewModel
import com.turkoglu.composedeneme.presentation.home.MovieListItem


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Movie) -> Unit
) {

    val popularMovies = viewModel.popularState.value.collectAsLazyPagingItems()
    val topratedMovies = viewModel.topretedState.value.collectAsLazyPagingItems()
    val nowPlayingMovies = viewModel.notPlayingState.value.collectAsLazyPagingItems()
    val upCommingMovies = viewModel.upCommingState.value.collectAsLazyPagingItems()
    val actionMovies = viewModel.actionState.value.collectAsLazyPagingItems()
    val animationMovies = viewModel.animationState.value.collectAsLazyPagingItems()
    val comedyMovies = viewModel.comedystate.value.collectAsLazyPagingItems()
    val dramaMovies = viewModel.dramaState.value.collectAsLazyPagingItems()
    val fantasyMovies = viewModel.fantasyState.value.collectAsLazyPagingItems()
    val historyMovies = viewModel.historyState.value.collectAsLazyPagingItems()
    val warMovie = viewModel.warState.value.collectAsLazyPagingItems()


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            //-------------------TopRated-----------------------
            item(content = {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Trending today", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"top_rated"}")
                        })

                }
            })
            item(content = {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyRow(content = {

                        itemsIndexed(
                            topratedMovies.itemSnapshotList.items,
                            key = { _: Int, movie: Movie -> movie.id }) { index, movie ->
                            MovieListItem(
                                modifier = modifier
                                    .height(200.dp)
                                    .width(180.dp),
                                movie = movie,
                                onMovieClick = {navigateToDetail(movie)}
                            )
                        }
                    })
                }
            })
            // ----------------Popular-----------------------
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Popular", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"popular"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            popularMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }
            // --------------NowPlaying----------------------

            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Now Playing", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"now_playing"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            nowPlayingMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }

            //------------Upcoming-----------------

            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "UpComing", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"upcoming"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            upCommingMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }
            //---Action
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Action", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"action"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            actionMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }
            //---Animation
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Animation", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"animation"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            animationMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }
            //---Comedy
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Comedy", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"comedy"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            comedyMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }

            //---Drama
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Drama", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"drama"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            dramaMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }

            //---Fantasy
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Fantasy", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"fantasy"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            fantasyMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }

            //---History
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "History", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"history"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

                        ) {

                        itemsIndexed(
                            historyMovies.itemSnapshotList.items,
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
                Spacer(modifier = modifier.height(10.dp))
            }

            //---War
            item {
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Sayfa kenarlarından padding
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "War", color = Color.White, fontSize = 18.sp)

                    Text(
                        text = "View All",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier.clickable {
                            navController.navigate("ViewAll/${"war"}")
                        })

                }
            }

            item {
                Spacer(modifier = modifier.height(5.dp))

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),

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
                Spacer(modifier = modifier.height(10.dp))
            }

        }
    }

}
