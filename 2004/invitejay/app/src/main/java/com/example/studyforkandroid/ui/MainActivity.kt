package com.example.studyforkandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.studyforkandroid.R
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import com.example.studyforkandroid.databinding.ActivityMainBinding
import com.example.studyforkandroid.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private val rvAdapter = MovieAdapter()
    private val viewModel = MovieViewModel(MovieRepositoryImpl)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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