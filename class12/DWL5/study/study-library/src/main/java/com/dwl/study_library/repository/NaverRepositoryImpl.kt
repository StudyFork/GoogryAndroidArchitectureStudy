package com.dwl.study_library.repository

import com.dwl.study_library.model.Movie
import com.dwl.study_library.source.NaverRemoteDataSource
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(private val remoteDataSource: NaverRemoteDataSource) :
    NaverRepository {
    override fun getMovies(
        q: String,
        success: (List<Movie.Item>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovies(q, success = success, error = error)
    }

}