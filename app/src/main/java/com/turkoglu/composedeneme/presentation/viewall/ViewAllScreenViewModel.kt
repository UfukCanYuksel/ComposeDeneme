package com.turkoglu.composedeneme.presentation.viewall

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.turkoglu.composedeneme.data.repo.MovieRepositoryImpl
import com.turkoglu.composedeneme.domain.model.Movie

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class ViewAllScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle ,
    private val movieRepository: MovieRepositoryImpl
    ):ViewModel() {

    private val _state = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val state : State<Flow<PagingData<Movie>>> = _state
    val useIncreasingPage = false

    private val _nameState = mutableStateOf(ViewAllState())
    val nameState : State<ViewAllState> = _nameState

    init {
        var selectedType=savedStateHandle.get<String>("selectedType") ?: ""
        getMovies(selectedType)
        nameState.value.movies = selectedType
    }



    private fun getMovies(selectedType : String){
        if (selectedType=="popular"){
                viewModelScope.launch{
                    _state.value =movieRepository.getMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        } else if(selectedType=="top_rated"){
                viewModelScope.launch {
                    _state.value = movieRepository.getTopRatedMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if(selectedType=="now_playing"){
                viewModelScope.launch {
                    _state.value = movieRepository.getNowPlayingMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else  if(selectedType == "upcoming"){
                viewModelScope.launch {
                    _state.value = movieRepository.getUpcomingMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if(selectedType == "action"){
                viewModelScope.launch {
                    _state.value = movieRepository.getActionMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if(selectedType == "animation"){

                viewModelScope.launch {
                    _state.value = movieRepository.getAnimationMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if(selectedType == "comedy"){

                viewModelScope.launch {
                    _state.value = movieRepository.getComedyMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if (selectedType == "drama"){

                viewModelScope.launch {
                    _state.value = movieRepository.getDramaMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if (selectedType == "fantasy"){
                viewModelScope.launch {
                    _state.value = movieRepository.getFantasyMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else if (selectedType == "history"){
                viewModelScope.launch {
                    _state.value = movieRepository.getHistoryMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }else{
                viewModelScope.launch {
                    _state.value = movieRepository.getWarMovies(useIncreasingPage).cachedIn(viewModelScope)
                }
        }
    }
}