package com.turkoglu.composedeneme.domain.use_case.get_movie_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import com.turkoglu.composedeneme.data.remote.dto.toMovie
import com.turkoglu.composedeneme.data.repo.MovieRepositoryImpl
import com.turkoglu.composedeneme.domain.model.MovieDetail
import com.turkoglu.composedeneme.domain.repo.MovieRepository
import com.turkoglu.composedeneme.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepositoryImpl) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun executeGetMovieDetail(imdbId:String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)
            emit(Resource.Success(movieDetail.toMovie()))

        }catch (e : IOException){
            emit(Resource.Error(message = "No internet connection"))
        }

    }
}
