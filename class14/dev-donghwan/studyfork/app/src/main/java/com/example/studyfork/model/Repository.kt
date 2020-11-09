package com.example.studyfork.model

interface Repository {
    fun searchMovie(query: String, success: () -> Unit, fail: () -> Unit)
}