package com.turkoglu.composedeneme.presentation.search.views

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SearchScreen(
    navController: NavController,
    //viewModelDetail: MovieDetailViewModel = hiltViewModel(),
    //viewModel: SearchViewModel = hiltViewModel()
) {

    //val state = viewModel.state.value
    //val stateDetail = viewModelDetail.state.value

    Box (modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){

        Column {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "Star Wars",
                onSearch = {
                    //viewModel.onEvent(MoviesEvent.Search(it))
                }
            )
            /*
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.movies){movie->
                    MovieListRow(movie = movie,onItemClick = {
                        //navigate to details
                        navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}")
                    }
                    )
                }
            }

             */
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier,
              hint : String = "Search...",
              onSearch : (String) -> Unit = {}) {
    var text by remember{ mutableStateOf("") }
    var isHintDisplayed by remember{ mutableStateOf(hint !="") }

    Box(modifier = modifier.padding(10.dp)){

        TextField (
            value = text,
            onValueChange = {
                text = it
            },
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text)
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(25.dp),
            modifier= Modifier.fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .onFocusChanged {
                    isHintDisplayed=it.isFocused != true && text.isEmpty()
                }
        )

        if (isHintDisplayed){
            Text(text=hint,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp))
        }
    }

}
