package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieMeta
import com.example.architecturestudy.data.source.remote.RemoteCallback

interface MovieRepository {
    fun remoteSearchMovie(title: String, searchMovieCallback: RemoteCallback<MovieMeta>)
}