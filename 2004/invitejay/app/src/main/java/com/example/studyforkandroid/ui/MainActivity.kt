package com.example.studyforkandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studyforkandroid.R
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvAdapter: MovieAdapter

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
        MovieRepositoryImpl.getRemoteMovieList(
            title,
            onSuccess = { movieList ->
                rvAdapter.clear()
                rvAdapter.addAll(movieList)
                rvAdapter.notifyDataSetChanged()
            },
            onFailure = { e ->
                e.printStackTrace()
            })
    }
}