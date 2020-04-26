package com.example.local.mapper

import com.example.data2.model.Movie
import com.example.local.MovieItem

interface Mapper {
    interface MovieMapper {
        fun LocalToData(items: List<MovieItem>): List<Movie>
        fun dataToLocal(items: List<Movie>): List<MovieItem>
    }
}