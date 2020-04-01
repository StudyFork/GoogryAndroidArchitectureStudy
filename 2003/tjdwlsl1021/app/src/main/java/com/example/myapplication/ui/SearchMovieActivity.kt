package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.myapplication.R
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_movie)
        movieRepositoryDataSet = application as MovieRepositoryDataSet
        binding.vm = viewModel

        initView()
        setOnclickListener()
        initViewModelCallback()
    }

    private fun initView() {
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = movieAdapter
    }

    private fun setOnclickListener() {
        movieAdapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }
    }

    private fun initViewModelCallback() {

        viewModel.run {
            emptyMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    viewModel.emptyMsg.get()?.run {
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
        }
    }

    fun showToast(res: Int) {
        showToast(getString(res))
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}