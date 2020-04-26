package com.example.remote.model.mapper

import com.example.data2.model.Movie

interface Mapper {
    interface MovieMapper {
        fun remoteToData(items: List<com.example.remote.model.Movie>): List<Movie>
    }
}