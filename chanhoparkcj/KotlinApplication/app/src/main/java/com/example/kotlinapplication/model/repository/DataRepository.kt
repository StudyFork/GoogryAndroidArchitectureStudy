package com.example.kotlinapplication.model.repository

interface DataRepository {
    fun getMovieResources(query: String)
    fun getImageResources(query: String)
    fun getBlogResources(query: String)
    fun getKinResources(query: String)
}