package com.example.architecturestudy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.databinding.ActivityMainBinding
import com.example.architecturestudy.presenter.SearchMovieConstract
import com.example.architecturestudy.presenter.SearchMoviePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchMovieConstract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private val searchMoviePresenter = SearchMoviePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.searchMoviePresenter = searchMoviePresenter

        init()
    }

    private fun init() {
        movieAdapter = MovieAdapter()

        movieRecyclerView.adapter = movieAdapter
    }

    override fun showMovieList(movieList: List<MovieData>) {
        movieAdapter.setData(movieList)
    }

    override fun showSearchFailToast(throwable: Throwable) {
        Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_SHORT).show()
    }
}