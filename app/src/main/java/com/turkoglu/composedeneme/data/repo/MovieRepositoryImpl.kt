package com.turkoglu.composedeneme.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.turkoglu.composedeneme.data.paging.PagingActionMovies
import com.turkoglu.composedeneme.data.paging.PagingActionMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingAnimationMovies
import com.turkoglu.composedeneme.data.paging.PagingAnimationMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingComedyMovies
import com.turkoglu.composedeneme.data.paging.PagingComedyMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingDramaMovies
import com.turkoglu.composedeneme.data.paging.PagingDramaMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingFantasyMovies
import com.turkoglu.composedeneme.data.paging.PagingFantasyMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingHistoryMovies
import com.turkoglu.composedeneme.data.paging.PagingHistoryMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingNowPlaying
import com.turkoglu.composedeneme.data.paging.PagingNowPlayingHome
import com.turkoglu.composedeneme.data.paging.PagingPopularMovies
import com.turkoglu.composedeneme.data.paging.PagingPopularMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingTopRatedMovies
import com.turkoglu.composedeneme.data.paging.PagingTopRatedMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingUpComingMovies
import com.turkoglu.composedeneme.data.paging.PagingUpComingMoviesHome
import com.turkoglu.composedeneme.data.paging.PagingWarMovies
import com.turkoglu.composedeneme.data.paging.PagingWarMoviesHome
import com.turkoglu.composedeneme.data.remote.MovieAPI
import com.turkoglu.composedeneme.data.remote.dto.MovieDetailDto
import com.turkoglu.composedeneme.data.remote.dto.MoviesDto
import com.turkoglu.composedeneme.domain.model.Movie
import com.turkoglu.composedeneme.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val useIncreasingPage: Boolean,private val api : MovieAPI){
    fun getMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingPopularMoviesHome(api)
                }else PagingPopularMovies(api)

            }
        ).flow
    }

    fun getTopRatedMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
               if (useIncreasingPage){
                   PagingTopRatedMoviesHome(api)
               }else PagingTopRatedMovies(api)
            }
        ).flow
    }

    fun getNowPlayingMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingNowPlayingHome(api)
                }else PagingNowPlaying(api)
            }
        ).flow
    }

    fun getUpcomingMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingUpComingMoviesHome(api)
                }else PagingUpComingMovies(api)
            }
        ).flow
    }

    suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }

    fun getActionMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingActionMoviesHome(api)
                }else PagingActionMovies(api)
            }
        ).flow
    }

    fun getAnimationMovies(useIncreasingPage: Boolean):Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingAnimationMoviesHome(api)
                }else PagingAnimationMovies(api)
            }
        ).flow
    }

    fun getComedyMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage) {
                    PagingComedyMoviesHome(api)
                }else PagingComedyMovies(api)
            }
        ).flow
    }

    fun getDramaMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingDramaMoviesHome(api)
                }else PagingDramaMovies(api)
            }
        ).flow
    }

    fun getFantasyMovies(useIncreasingPage: Boolean):Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders =  false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingFantasyMoviesHome(api)
                }else PagingFantasyMovies(api)
            }
        ).flow
    }

    fun getHistoryMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage) {
                    PagingHistoryMoviesHome(api)
                }else PagingHistoryMovies(api)
            }
        ).flow
    }

    fun getWarMovies(useIncreasingPage: Boolean): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false , pageSize = 20) ,
            pagingSourceFactory = {
                if (useIncreasingPage){
                    PagingWarMoviesHome(api)
                }else PagingWarMovies(api)
            }
        ).flow
    }


}