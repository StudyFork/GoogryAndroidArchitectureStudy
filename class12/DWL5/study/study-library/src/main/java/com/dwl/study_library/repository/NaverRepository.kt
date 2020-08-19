package com.dwl.study_library.repository

import com.dwl.study_library.model.Movie


interface NaverRepository {
    fun getMovies(q :String, success:(List<Movie.Item>) -> Unit, error:(Throwable) -> Unit)
}