package com.world.tree

import com.world.tree.architecturestudy.BASE_URL
import com.world.tree.architecturestudy.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieContainer () {
    private val movieService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)

}