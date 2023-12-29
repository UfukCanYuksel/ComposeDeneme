package com.turkoglu.composedeneme.presentation.home

import com.turkoglu.composedeneme.domain.model.Movie

data class HomeScreenStateUpcoming(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<Movie>  = emptyList(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)