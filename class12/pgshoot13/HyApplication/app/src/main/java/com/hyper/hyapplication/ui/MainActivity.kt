package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.model.ResultGetSearchMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View {

    private lateinit var viewAdapter: MovieAdapter
    private val presenter = MainPresenter(this)

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
        viewAdapter.resetData(item)
    }

    override fun showFailure(it: Throwable) {
        Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyMessage() {
        Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
    }
}


