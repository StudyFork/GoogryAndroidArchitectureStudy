package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repository.MovieRepositoryDataSet
import com.example.myapplication.databinding.ActivitySearchMovieBinding

class SearchMovieActivity : AppCompatActivity() {

    private lateinit var movieRepositoryDataSet: MovieRepositoryDataSet
    private val movieAdapter = SearchMovieAdapter()
    private lateinit var binding: ActivitySearchMovieBinding

    private val viewModel: SearchMovieViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchMovieViewModel(movieRepositoryDataSet.movieRepository) as T
            }
        }
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

        binding.lifecycleOwner = this
        viewModel.failMsg.observe(this, Observer {
            if (!it.first) {
                if (it.second is Int) {
                    showToast(it.second as Int)
                } else if (it.second is String) {
                    showToast(it.second as String)
                }
            }
        })

        viewModel.movieList.observe(this, Observer {
            this@SearchMovieActivity.movieAdapter.addItems(viewModel.movieList.value)
        })

        setContentView(binding.root)
    }

    fun showToast(res: Int) {
        showToast(getString(res))
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}