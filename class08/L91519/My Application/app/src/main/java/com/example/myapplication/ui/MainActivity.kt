package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter by lazy { MainPresenter(this) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieRecyclerViewAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

        adapter = MovieRecyclerViewAdpater() { link ->
            binding.webviewDetailMovie.loadUrl(link)
        }

        binding.rvMovieList.adapter = adapter

        resentData()
    }

    override fun updateMovieRecycler(items: List<MovieResult.Item>) {
        adapter.setItems(items)
    }

    override fun failMovieGet(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun findMovie() {
        presenter.findMovie(binding.etMovie.text.toString())
    }

    override fun queryNone() {
        Toast.makeText(applicationContext, getString(R.string.query_none), Toast.LENGTH_SHORT)
            .show()
    }

    override fun resultNone() {
        Toast.makeText(applicationContext, getString(R.string.result_none), Toast.LENGTH_SHORT)
            .show()
    }

    override fun resentData() {
        presenter.resentData()
    }

    override fun saceCache() {

    }
}

