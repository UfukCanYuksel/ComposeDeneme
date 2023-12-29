package com.turkoglu.composedeneme.domain.use_case.get_now_playing

import android.os.Build
import androidx.annotation.RequiresExtension
import com.turkoglu.composedeneme.data.remote.dto.toMovieList
import com.turkoglu.composedeneme.domain.model.Movie
import com.turkoglu.composedeneme.domain.repo.MovieRepository
import com.turkoglu.composedeneme.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val repository: MovieRepository) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun executeGetNowPlayingMovies(page : Int) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getNowPlayingMovies(page)
            if (movieList.results.isNotEmpty()){
                emit(Resource.Success(movieList.toMovieList()))

            }else{
                emit(Resource.Error(message = "No Movie Found!"))
            }

        }catch (e : IOException){
            emit(Resource.Error(message = "No internet connection"))
        }

    }
}