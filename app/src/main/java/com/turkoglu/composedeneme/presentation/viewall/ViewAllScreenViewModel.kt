package com.turkoglu.composedeneme.presentation.viewall

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turkoglu.composedeneme.domain.use_case.get_movies.GetMovieUseCase
import com.turkoglu.composedeneme.domain.use_case.get_now_playing.GetNowPlayingUseCase
import com.turkoglu.composedeneme.domain.use_case.get_toprated.GetTopRatedUseCase
import com.turkoglu.composedeneme.domain.use_case.get_upcoming.GetUpcomingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class ViewAllScreenViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val savedStateHandle: SavedStateHandle

    ):ViewModel() {
    private val _state = mutableStateOf(ViewAllState())
    val state: State<ViewAllState> = _state



    init {
        var selectedType=savedStateHandle.get<String>("selectedType") ?: ""
        getMovies(selectedType)
    }

    private fun getMovies(selectedType : String){

        if (selectedType=="popular"){
            getMovieUseCase.executeGetMovies(1).onEach{
                _state.value = ViewAllState(movies = it.data ?: emptyList())

            }.launchIn(viewModelScope)
        } else if(selectedType=="top_rated"){
            getTopRatedUseCase.executeGetTopRatedMovies(1).onEach {
                _state.value = ViewAllState(movies = it.data ?: emptyList())
            }.launchIn(viewModelScope)
        }else if(selectedType=="now_playing"){
            getNowPlayingUseCase.executeGetNowPlayingMovies(1).onEach {
                _state.value = ViewAllState(movies = it.data ?: emptyList())
            }.launchIn(viewModelScope)
        }else {
            getUpcomingUseCase.executeGetUpcomingMovies(1).onEach {
                _state.value = ViewAllState(movies = it.data ?: emptyList())
            }.launchIn(viewModelScope)
        }
    }
}