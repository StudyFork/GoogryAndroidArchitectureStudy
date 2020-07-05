package com.example.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.MovieData
import com.example.architecturestudy.data.MovieMeta
import com.example.architecturestudy.network.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val movieAdapter = MovieAdapter()

        movieRecyclerView.adapter = movieAdapter

        searchButton.setOnClickListener {
            searchEditText.text?.toString()?.let { title ->
                MovieApiService.movieApiService.searchMovie(title)
                    .enqueue(object : Callback<MovieMeta> {
                        override fun onFailure(call: Call<MovieMeta>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<MovieMeta>,
                            response: Response<MovieMeta>
                        ) {
                            if (response.isSuccessful) {
                                val metaData = response.body()

                                metaData?.let {
                                    val movieList = ArrayList<MovieData>()
                                    if (it.items.isNotEmpty()) {
                                        movieList.addAll(it.items)
                                        movieAdapter.setData(movieList = movieList)
                                    }
                                }
                            }
                        }
                    })
            }
        }
    }
}