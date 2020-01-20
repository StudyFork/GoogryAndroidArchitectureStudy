package com.studyfork.architecturestudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.studyfork.architecturestudy.R
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.extension.hideKeyboard
import com.studyfork.architecturestudy.ui.adapter.MovieResultRVAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainPresenter by lazy {
        MainPresenter(this)
    }

    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMovieRecyclerView()

        btn_search.setOnClickListener {
            currentFocus?.hideKeyboard()
            presenter.getMovieList(edit_movie_search.text.toString())
        }
    }

    private fun setMovieRecyclerView() {
        rv_movie_list.run {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }

    override fun showMovieList(items: MovieResponse) {
        movieResultRVAdapter.setItems(items.items)
    }

    override fun showErrorEmptyQuery() {
        Toast.makeText(baseContext, getString(R.string.empty_query_notice), Toast.LENGTH_SHORT).show()
    }

    override fun showErrorEmptyResult() {
        Toast.makeText(baseContext, getString(R.string.empty_data_notice), Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        pb_loading_view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_loading_view.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onViewDetached()
        super.onDestroy()
    }
}
