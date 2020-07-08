package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*

const val CLIENT_ID = "ImN25OL4axIIwuH6jXDj"
const val CLIENT_SECRET = "vysJ6N1WXx"
const val BASE_URL = "https://openapi.naver.com"

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MovieAdapter
    private val moviList = NaverRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = MovieAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        searchButton.setOnClickListener {
            if (searchText.text.toString().isNotEmpty()) {
                moviList.movieSearch(
                    searchText.text.toString(),
                    success = { viewAdapter.resetData(it) },
                    failure = {
                        Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
                    })
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()
            }
        }
    }
}


