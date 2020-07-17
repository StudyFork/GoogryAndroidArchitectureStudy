package com.world.tree.architecturestudy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.world.tree.architecturestudy.CommonApplication
import com.world.tree.architecturestudy.MovieContainer
import com.world.tree.architecturestudy.R
import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.presenter.MovieContract
import com.world.tree.architecturestudy.presenter.MoviePresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {
    private lateinit var movieContainer: MovieContainer
    private lateinit var adapter: MovieAdapter
    private lateinit var presenter: MovieContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieContainer = (application as CommonApplication).movieContainer
        presenter = MoviePresenterImpl( this, movieContainer.repository)
        adapter = MovieAdapter()
        recyclerView.adapter = adapter

        btnSearch.setOnClickListener {
            presenter.searchMovie(etUrl.text.toString())
        }
    }

    override fun showToast(m: String) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
    }

    override fun setMovieData(list: List<Movie.Item>) {
        adapter.addData(list)
    }

    override fun clearList() {
        adapter.clearData()
    }
}
