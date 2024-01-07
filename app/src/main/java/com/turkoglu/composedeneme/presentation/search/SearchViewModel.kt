package com.turkoglu.composedeneme.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turkoglu.composedeneme.data.repo.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repositoryImpl: MovieRepositoryImpl
) : ViewModel() {

    fun getSearch(search : String){
        viewModelScope.launch {
            val x = repositoryImpl.getSearch(search)
        }
    }
}