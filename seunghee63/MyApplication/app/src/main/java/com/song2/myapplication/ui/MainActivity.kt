package com.song2.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.song2.myapplication.R
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.source.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieRepository by lazy { MovieRepositoryImpl() }
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

        movieRepository.getMovieData(keyword, 30,
            onSuccess = { movieAdapter.setMovieList(it) },
            onFailure = { Log.e("실패", it.toString()) }
        )
    }
}