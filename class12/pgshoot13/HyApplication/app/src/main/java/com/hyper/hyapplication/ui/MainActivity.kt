package com.hyper.hyapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.model.ResultGetSearchMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View {

    private lateinit var viewAdapter: MovieAdapter
    private val moviList = NaverRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


