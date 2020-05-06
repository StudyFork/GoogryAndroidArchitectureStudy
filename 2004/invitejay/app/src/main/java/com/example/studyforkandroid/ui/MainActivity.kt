package com.example.studyforkandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.studyforkandroid.R
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.databinding.ActivityMainBinding
import com.example.studyforkandroid.presenter.MovieContract
import com.example.studyforkandroid.presenter.MoviePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {

    private val rvAdapter = MovieAdapter()
    private val moviePresenter = MoviePresenter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this@MainActivity
        moviePresenter.setView(this)

        initRv()
    }

    fun searchBtn() {
        moviePresenter.loadItem(movie_input.text.toString())
    }

    private fun initRv() {
        binding.movieRv.adapter = rvAdapter
    }

    override fun setItems(list: List<Movie>) {
        rvAdapter.replaceAll(list)
    }
}