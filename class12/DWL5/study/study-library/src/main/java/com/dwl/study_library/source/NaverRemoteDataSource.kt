package com.dwl.study_library.source

import com.dwl.study_library.model.Movie


interface NaverRemoteDataSource {
    fun getMovies(q : String, success:(List<Movie.Item>) -> Unit,
                  error: (Throwable) -> Unit)
}