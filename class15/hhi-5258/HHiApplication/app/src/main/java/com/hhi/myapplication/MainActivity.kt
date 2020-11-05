package com.hhi.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hhi.myapplication.api.MovieData
import com.hhi.myapplication.api.NaverAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val recyclerAdapter = RecyclerAdapter()
    private val api = NaverAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        main_recyclerview.setHasFixedSize(false)
        main_recyclerview.adapter = recyclerAdapter
        main_btn_search.setOnClickListener {
            if (main_edit_search.text.isNotEmpty()) {
                searchMovie(main_edit_search.text.toString())
            }
        }
    }

    private fun searchMovie(query: String) {
        api.searchMovies(query).enqueue(object : retrofit2.Callback<MovieData.Response> {
            override fun onResponse(
                call: Call<MovieData.Response>,
                response: Response<MovieData.Response>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recyclerAdapter.movieList = it.items
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<MovieData.Response>, t: Throwable) {
                Log.d("search_failed", t.toString());
            }
        })
    }
}