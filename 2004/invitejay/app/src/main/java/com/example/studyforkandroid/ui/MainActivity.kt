package com.example.studyforkandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyforkandroid.R
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import com.example.studyforkandroid.databinding.ActivityMainBinding
import com.example.studyforkandroid.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private val rvAdapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieViewModel(MovieRepositoryImpl) as T
            }
        }).get(MovieViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
        initRv()
    }

    private fun initRv() {
        binding.movieRv.adapter = rvAdapter
    }
}