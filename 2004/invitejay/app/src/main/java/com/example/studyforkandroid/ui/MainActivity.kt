package com.example.studyforkandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.studyforkandroid.R
import com.example.studyforkandroid.adapter.MovieAdapter
import com.example.studyforkandroid.databinding.ActivityMainBinding
import com.example.studyforkandroid.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val rvAdapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            vm = movieViewModel
            lifecycleOwner = this@MainActivity
        }

        initRv()
    }

    private fun initRv() {
        binding.movieRv.adapter = rvAdapter
    }
}