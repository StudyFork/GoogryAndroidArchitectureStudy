package com.example.studyforkandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.MovieRes
import com.example.studyforkandroid.network.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRv()
        initBtn()
    }

    private fun initBtn() {
        movie_search.setOnClickListener {
            searchMovie(movie_input.text.toString())
        }
    }

    private fun initRv() {
        rvAdapter = MovieAdapter()
        movie_rv.adapter = rvAdapter
    }

    private fun searchMovie(title: String) {

        RetrofitService.instance
            .movieRequest(title)
            .enqueue(object : Callback<MovieRes> {
                override fun onFailure(call: Call<MovieRes>, t: Throwable) {
                    Log.d("resposeFailure", "movieList")
                }

                override fun onResponse(call: Call<MovieRes>, response: Response<MovieRes>) {
                    val movie = response.body()
                    movie?.let {
                        rvAdapter.clear()
                        rvAdapter.addAll(it.items)
                        rvAdapter.notifyDataSetChanged()
                    }
                }
            })
    }
}