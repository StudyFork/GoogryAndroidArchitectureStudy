package com.olaf.nukeolaf.data.remote

interface MovieRemoteDataSource {
    fun getMovie(query: String)
}