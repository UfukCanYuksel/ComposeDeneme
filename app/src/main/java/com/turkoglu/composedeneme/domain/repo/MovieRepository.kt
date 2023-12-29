package com.turkoglu.composedeneme.domain.repo

import androidx.paging.PagingData
import com.turkoglu.composedeneme.data.remote.dto.MovieDetailDto
import com.turkoglu.composedeneme.data.remote.dto.MoviesDto
import com.turkoglu.composedeneme.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getTopRatedMovies(): Flow<PagingData<Movie>>
    suspend fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetail(imdbId:String): MovieDetailDto
    suspend fun getActionMovies() : Flow<PagingData<Movie>>
    suspend fun getAnimationMovies() : Flow<PagingData<Movie>>
    suspend fun getComedyMovies() : Flow<PagingData<Movie>>
    suspend fun getDramaMovies() : Flow<PagingData<Movie>>
    suspend fun getFantasyMovies() : Flow<PagingData<Movie>>
    suspend fun getHistoryMovies() : Flow<PagingData<Movie>>
    suspend fun getWarMovies() : Flow<PagingData<Movie>>


}