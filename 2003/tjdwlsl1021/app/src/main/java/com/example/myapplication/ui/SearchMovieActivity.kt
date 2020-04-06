package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import com.example.myapplication.data.repository.MovieRepositoryDataSet
import com.example.myapplication.databinding.ActivitySearchMovieBinding

class SearchMovieActivity : AppCompatActivity() {

    private lateinit var movieRepositoryDataSet: MovieRepositoryDataSet
    private val movieAdapter = SearchMovieAdapter()
    private lateinit var binding: ActivitySearchMovieBinding

    private val viewModel by lazy {
        SearchMovieViewModel(movieRepositoryDataSet.movieRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieRepositoryDataSet = application as MovieRepositoryDataSet

        binding = ActivitySearchMovieBinding.inflate(layoutInflater).apply {
            vm = viewModel
            adapter = this@SearchMovieActivity.movieAdapter
        }

        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = movieAdapter

        movieAdapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }

        viewModel.run {
            failMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    viewModel.failMsg.get()?.run {
                        if (!first) {
                            if (second is Int) {
                                showToast(second as Int)
                            } else if (second is String) {
                                showToast(second as String)
                            }
                        } else {
                            return
                        }
                    }
                }
            })
            movieList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    this@SearchMovieActivity.movieAdapter.addItems(movieList.get())
                }

            })
        }
        setContentView(binding.root)
    }

    fun showToast(res: Int) {
        showToast(getString(res))
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}