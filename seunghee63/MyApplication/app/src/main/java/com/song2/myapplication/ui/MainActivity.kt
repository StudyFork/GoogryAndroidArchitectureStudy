package com.song2.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.song2.myapplication.R
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.data.MovieData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter by lazy { MovieAdapter(context = this@MainActivity) }
    private val dataList = arrayListOf<MovieData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieRecyclerView()
    }

    private fun setMovieRecyclerView(){

        dataList.add(MovieData("title","l","a","a","a","a","a"))
        dataList.add(MovieData("title","l","a","a","a","a","a"))
        dataList.add(MovieData("title","l","a","a","a","a","a"))
        dataList.add(MovieData("title","l","a","a","a","a","a"))

        movieAdapter.apply {
            data = dataList
        }

        rv_main_act_movie_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
    }

}
