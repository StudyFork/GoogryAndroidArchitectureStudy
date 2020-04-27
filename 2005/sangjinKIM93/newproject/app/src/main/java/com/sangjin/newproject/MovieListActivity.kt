package com.sangjin.newproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sangjin.newproject.adapter.Movie
import com.sangjin.newproject.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private var movieList = arrayOf<Movie>()
    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter(movieList)
    }
    private val linearLayoutManager = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        //리사이클러뷰 초기화
        movieListView.layoutManager = linearLayoutManager
        movieListView.adapter = movieListAdapter

    }
}
