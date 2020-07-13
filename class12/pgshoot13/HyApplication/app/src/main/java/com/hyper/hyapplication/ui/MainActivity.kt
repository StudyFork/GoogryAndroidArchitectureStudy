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
            presenter.movieSearch(searchList)
        }
    }

    override fun showMovie(item: List<ResultGetSearchMovie.Item>) {

    }

    override fun showFailure(it: Throwable) {

    }

    override fun showEmptyMessage() {

    }
}


