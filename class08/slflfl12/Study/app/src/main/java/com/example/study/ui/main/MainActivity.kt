package com.example.study.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.study.R
import com.example.study.data.model.Movie
import com.example.study.ui.adapter.MovieAdapter
import com.example.study.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val movieAdapter = MovieAdapter()
    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.adapter = movieAdapter

        btn_search.setOnClickListener {
            if (et_movie_search.text.isNullOrEmpty()) {
                showErrorQueryEmpty()
            } else {
                getMovieList(et_movie_search.text.toString())
            }
        }

        movieAdapter.setOnItemClickListener { movie ->
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_URL, movie.link.toString())

            this.startActivity(intent)
        }
    }

    private fun getMovieList(query: String) {
        presenter.getMovies(query)
    }

    override fun updateMovieList(items: List<Movie>) {
        movieAdapter.setItem(items)
    }

    override fun showProgress() {
        pb_loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_loading.visibility = View.GONE
    }

    override fun showErrorQueryEmpty() {
        Toast.makeText(this@MainActivity, R.string.empty_query_message, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorEmptyResult() {
        Toast.makeText(this@MainActivity, R.string.empty_result_message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}




