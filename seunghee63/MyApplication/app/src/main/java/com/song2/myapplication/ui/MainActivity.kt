package com.song2.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.song2.myapplication.R
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.data.MovieDataResponse
import com.song2.myapplication.data.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val movieRepository: MovieRepository = MovieRepository()

    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieRecyclerView()

        btn_main_act_search_btn.setOnClickListener {
            getMovieData(et_main_act_search.text.toString())
        }
    }

    private fun setMovieRecyclerView() {

        rv_main_act_movie_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

    }

    private fun getMovieData(keyword: String) {

        movieRepository.getMovieData(keyword, 30)
            .enqueue(object : Callback<MovieDataResponse> {
                override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
                    Log.e("실패", t.toString())
                }

                override fun onResponse(
                    call: Call<MovieDataResponse>,
                    response: Response<MovieDataResponse>
                ) {
                    if (response.isSuccessful) {

                        movieAdapter.apply {
                            data = response.body()!!.items
                        }

                        rv_main_act_movie_list.apply {
                            movieAdapter.notifyDataSetChanged()
                        }

                        Log.e("성공", response.body()!!.items.toString())
                    }
                }
            })


    }
}