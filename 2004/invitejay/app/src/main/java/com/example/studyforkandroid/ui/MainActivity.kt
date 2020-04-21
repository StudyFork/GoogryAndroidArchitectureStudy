package com.example.studyforkandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studyforkandroid.R
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.presenter.MovieContract
import com.example.studyforkandroid.presenter.MoviePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {

    private val rvAdapter = MovieAdapter()
    private val moviePresenter = MoviePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviePresenter.setView(this)

        initRv()
        initBtn()
    }

    private fun initBtn() {
        movie_search.setOnClickListener {
            moviePresenter.loadItem(movie_input.text.toString())
        }
    }

    private fun initRv() {
        movie_rv.adapter = rvAdapter
    }

    override fun showMovieList(list: List<Movie>) {
        rvAdapter.clear()
        rvAdapter.addAll(list)
        rvAdapter.notifyDataSetChanged()
    }
}