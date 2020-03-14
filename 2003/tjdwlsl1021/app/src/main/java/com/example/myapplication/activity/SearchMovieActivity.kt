package com.example.myapplication.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.SearchMovieAdapter
import com.example.myapplication.data.repository.MovieRepositoryDataSet
import kotlinx.android.synthetic.main.activity_search_movie.*

class SearchMovieActivity : AppCompatActivity() {
    private val TAG = "SearchMovieActivity"

    private lateinit var MovieRepositoryDataSet: MovieRepositoryDataSet

    private val movieAdapter = SearchMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        initview()
        setOnclickListener()
    }

    private fun initview() {
        MovieRepositoryDataSet = application as MovieRepositoryDataSet
        rv_movie.setHasFixedSize(true)
        rv_movie.adapter = movieAdapter
    }

    private fun setOnclickListener() {
        btn_search.setOnClickListener(View.OnClickListener {
            var etMovieTitle = et_movie_title.text.toString()

            if (etMovieTitle.isNotEmpty()) {
                getMovieList(etMovieTitle)
            } else {
                Toast.makeText(this, R.string.activity_toast_empty_movie_title, Toast.LENGTH_SHORT).show()
            }
        })

        movieAdapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }
    }

    private fun getMovieList(query: String) {
        MovieRepositoryDataSet.mMovieRepository.getMovieList(query, success = { movieAdapter.addItems(it) }, failed = { Log.e(TAG, it.toString()) })
    }
}