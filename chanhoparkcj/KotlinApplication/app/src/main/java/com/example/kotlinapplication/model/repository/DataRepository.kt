package com.example.kotlinapplication.model.repository

import com.example.kotlinapplication.model.*
import io.reactivex.Single

interface DataRepository {
    fun callMovieResources(query: String): Single<ResponseItems<MovieItems>>
    fun callImageResources(query: String): Single<ResponseItems<ImageItems>>
    fun callBlogResources(query: String): Single<ResponseItems<BlogItems>>
    fun callKinResources(query: String): Single<ResponseItems<KinItems>>
}