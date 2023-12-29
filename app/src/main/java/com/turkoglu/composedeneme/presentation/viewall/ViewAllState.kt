package com.turkoglu.composedeneme.presentation.viewall

import com.turkoglu.composedeneme.domain.model.Movie

data class ViewAllState (
    var movies: List<Movie>  = emptyList()
)