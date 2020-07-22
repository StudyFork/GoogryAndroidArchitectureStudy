package com.hyper.hyapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyper.hyapplication.MovieAdapter
import com.hyper.hyapplication.R
import com.hyper.hyapplication.databinding.ActivityMainBinding
import com.hyper.hyapplication.model.ResultGetSearchMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View {

    private lateinit var viewAdapter: MovieAdapter
    private val presenter = MainPresenter(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity, R.layout.activity_main
        )
        binding.view = this
        binding.presenter = presenter

        viewAdapter = MovieAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
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

    override fun searchMovie(movieName: String) {
        presenter.movieSearch(movieName)
    }
}


