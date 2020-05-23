package com.eunice.eunicehong.data.source

import com.eunice.eunicehong.data.model.MovieContents

interface MovieDataSource {
    interface LoadMoviesCallback {
        fun onSuccess(query: String?, movieContents: MovieContents)
        fun onFailure(e: Throwable)
    }
}