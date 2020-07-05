package com.example.architecturestudy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.repository.MovieRespositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieRepository = MovieRespositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val movieAdapter = MovieAdapter()

        movieRecyclerView.adapter = movieAdapter

        searchButton.setOnClickListener {
            searchEditText.text?.toString()?.let { title ->
                movieRepository.remoteSearchMovie(
                    title,
                    success = { movieList -> movieAdapter.setData(movieList) },
                    fail = { t ->
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}