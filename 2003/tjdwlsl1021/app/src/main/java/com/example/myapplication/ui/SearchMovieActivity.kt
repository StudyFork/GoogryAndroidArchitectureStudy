package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivitySearchMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMovieActivity : AppCompatActivity() {

    private val movieAdapter = SearchMovieAdapter()
    private lateinit var binding: ActivitySearchMovieBinding

    private val viewModel: SearchMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            movieAdapter.addItems(viewModel.movieList.value)
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