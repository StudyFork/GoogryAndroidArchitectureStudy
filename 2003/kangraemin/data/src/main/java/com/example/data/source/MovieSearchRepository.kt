package com.example.data.source

import com.example.data.model.Movie
import io.reactivex.Flowable

interface MovieSearchRepository {
    fun getMovieData(query: String): Flowable<List<Movie>>
}