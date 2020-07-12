package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*

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
            val searchList = searchText.text.toString()
            if (searchList.isNotEmpty()) {
                moviList.movieSearch(
                    searchList,
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


