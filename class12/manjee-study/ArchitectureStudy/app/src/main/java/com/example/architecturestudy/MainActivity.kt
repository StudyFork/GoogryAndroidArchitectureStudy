package com.example.architecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.data.MovieMeta
import com.example.architecturestudy.network.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieApiService = MovieApiService()
        movieApiService.createService().searchMovie("미션").enqueue(object : Callback<MovieMeta> {
            override fun onFailure(call: Call<MovieMeta>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieMeta>, response: Response<MovieMeta>) {

            }
        })
    }
}