package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.source.remote.MovieRemoteService
import com.example.architecturestudy.data.source.remote.RemoteCallback

class MovieRespositoryImpl : MovieRepository {
    override fun remoteSearchMovie(title: String, searchMovieCallback: RemoteCallback) {
        MovieRemoteService.movieApiService.searchMovie(title).enqueue(searchMovieCallback)
    }
}