package com.turkoglu.composedeneme.presentation.home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.turkoglu.composedeneme.data.repo.MovieRepositoryImpl
import com.turkoglu.composedeneme.domain.model.Movie
import com.turkoglu.composedeneme.domain.repo.MovieRepository
import com.turkoglu.composedeneme.domain.use_case.get_movies.GetMovieUseCase
import com.turkoglu.composedeneme.domain.use_case.get_now_playing.GetNowPlayingUseCase
import com.turkoglu.composedeneme.domain.use_case.get_toprated.GetTopRatedUseCase
import com.turkoglu.composedeneme.domain.use_case.get_upcoming.GetUpcomingUseCase
import com.turkoglu.composedeneme.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl ,
    private val getMovieUseCase: GetMovieUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,

) : ViewModel() {

    val useIncreasingPage = true

    private val _popularState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val popularState : State<Flow<PagingData<Movie>>> = _popularState

    private val _topratedState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val topretedState : State<Flow<PagingData<Movie>>> = _topratedState

    private val _nowPlayingState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val notPlayingState : State<Flow<PagingData<Movie>>> = _nowPlayingState

    private val _upCommingState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val upCommingState : State<Flow<PagingData<Movie>>> = _upCommingState

    private val _actionState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val actionState : State<Flow<PagingData<Movie>>> = _actionState

    private val _animationState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val animationState : State<Flow<PagingData<Movie>>> = _animationState

    private val _comedyState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val comedystate : State<Flow<PagingData<Movie>>> = _comedyState

    private val _dramaState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val dramaState : State<Flow<PagingData<Movie>>> = _dramaState

    private val _fantasyState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val fantasyState : State<Flow<PagingData<Movie>>> = _fantasyState

    private val _historyState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val historyState : State<Flow<PagingData<Movie>>> = _historyState

    private val _warState = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val warState : State<Flow<PagingData<Movie>>> = _warState


/*
    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private val _stateTopRated = mutableStateOf(HomeScreenStateTopRated())
    val stateTopRated: State<HomeScreenStateTopRated> = _stateTopRated

    private val _stateNowPlaying = mutableStateOf(HomeScreenStateTopRated())
    val stateNowPlaying: State<HomeScreenStateTopRated> = _stateNowPlaying

    private val _stateUpcoming = mutableStateOf(HomeScreenStateTopRated())
    val stateUpcoming: State<HomeScreenStateTopRated> = _stateUpcoming


 */
    private val page = 1

    init {
        /*
        getMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
        getUpcomingMovies()

         */
        getPopularMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
        getUpCommingMovies()
        getActionMovies()
        getAnimationMovies()
        getComedyMovies()
        getDramaMovies()
        getFantasyMovies()
        getHistoryMovies()
        getWarMovies()

    }

    fun getPopularMovies(){
        viewModelScope.launch{
            _popularState.value =movieRepository.getMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            _topratedState.value = movieRepository.getTopRatedMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getNowPlayingMovies(){
        viewModelScope.launch {
            _nowPlayingState.value = movieRepository.getNowPlayingMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getUpCommingMovies(){
        viewModelScope.launch {
            _upCommingState.value = movieRepository.getUpcomingMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getActionMovies(){
        viewModelScope.launch {
            _actionState.value = movieRepository.getActionMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getAnimationMovies(){
        viewModelScope.launch {
            _animationState.value = movieRepository.getAnimationMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getComedyMovies(){
        viewModelScope.launch {
            _comedyState.value = movieRepository.getComedyMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getDramaMovies(){
        viewModelScope.launch {
            _dramaState.value = movieRepository.getDramaMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getFantasyMovies(){
        viewModelScope.launch {
            _fantasyState.value = movieRepository.getFantasyMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getHistoryMovies(){
        viewModelScope.launch {
            _historyState.value = movieRepository.getHistoryMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }
    fun getWarMovies(){
        viewModelScope.launch {
            _warState.value = movieRepository.getWarMovies(useIncreasingPage).cachedIn(viewModelScope)
        }
    }

/*
    private fun getMovies() {
        getMovieUseCase.executeGetMovies(page = page).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeScreenState(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = HomeScreenState(errorMessage = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = HomeScreenState(loading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getTopRatedMovies() {
        getTopRatedUseCase.executeGetTopRatedMovies(page = page).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateTopRated.value = HomeScreenStateTopRated(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _stateTopRated.value =
                        HomeScreenStateTopRated(errorMessage = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateTopRated.value = HomeScreenStateTopRated(loading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getNowPlayingMovies() {
        getNowPlayingUseCase.executeGetNowPlayingMovies(page = page).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateNowPlaying.value =
                        HomeScreenStateTopRated(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _stateNowPlaying.value =
                        HomeScreenStateTopRated(errorMessage = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateNowPlaying.value = HomeScreenStateTopRated(loading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getUpcomingMovies() {
        getUpcomingUseCase.executeGetUpcomingMovies(page = page).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateUpcoming.value = HomeScreenStateTopRated(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _stateUpcoming.value =
                        HomeScreenStateTopRated(errorMessage = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateUpcoming.value = HomeScreenStateTopRated(loading = true)
                }
            }
        }.launchIn(viewModelScope)

    }


 */
}