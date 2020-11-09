package com.hhi.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hhi.myapplication.data.model.MovieData
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
            val searchText = main_edit_search.text
            if (searchText.isNotEmpty()) {
                searchMovie(searchText.toString())
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
                        recyclerAdapter.setMovieList(it.items)
                    }
                }
            }

            override fun onFailure(call: Call<MovieData.Response>, t: Throwable) {
                Log.d("search_failed", t.toString());
            }
        })
    }
}