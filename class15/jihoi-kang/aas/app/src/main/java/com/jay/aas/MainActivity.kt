package com.jay.aas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jay.aas.api.MovieResponse
import com.jay.aas.api.MovieService
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.model.Movie
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG = this::class.java.simpleName

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun searchMovies(query: String) {
        launch {
            val movies = search(query)
        }
    }

    private suspend fun search(query: String) =
        suspendCancellableCoroutine<List<Movie>> { continuation ->
            val movieService = RetrofitHelper.getRetrofit().create(MovieService::class.java)
            movieService.searchMovies(query).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        continuation.resume(response.body()?.items ?: emptyList())
                    } else {
                        continuation.cancel()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    continuation.cancel(t)
                }

            })
        }

}