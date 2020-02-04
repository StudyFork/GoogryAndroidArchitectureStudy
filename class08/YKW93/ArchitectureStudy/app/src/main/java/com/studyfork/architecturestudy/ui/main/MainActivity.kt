package com.studyfork.architecturestudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.studyfork.architecturestudy.R
import com.studyfork.architecturestudy.base.BaseActivity
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.databinding.ActivityMainBinding
import com.studyfork.architecturestudy.extension.hideKeyboard
import com.studyfork.architecturestudy.ui.adapter.MovieResultRVAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainPresenter>(R.layout.activity_main),
    MainContract.View {

    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override val presenter: MainPresenter = MainPresenter(this)

    override val progressBar: ProgressBar
        get() = binding.pbLoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMovieRecyclerView()

        binding.setListener {
            currentFocus?.hideKeyboard()
            presenter.getMovieList(binding.editMovieSearch.text.toString())
        }
    }

    override fun showMovieList(items: MovieResponse) {
        movieResultRVAdapter.setItems(items.items)
    }

    override fun showErrorEmptyQuery() {
        Toast.makeText(baseContext, getString(R.string.empty_query_notice), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showErrorEmptyResult() {
        Toast.makeText(baseContext, getString(R.string.empty_data_notice), Toast.LENGTH_SHORT)
            .show()
    }

    private fun setMovieRecyclerView() {
        binding.rvMovieList.run {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }
}
