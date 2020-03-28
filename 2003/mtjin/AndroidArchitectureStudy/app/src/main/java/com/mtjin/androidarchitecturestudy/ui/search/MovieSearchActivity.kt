package com.mtjin.androidarchitecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.search.Movie
import com.mtjin.androidarchitecturestudy.databinding.ActivityMovieSearchBinding
import com.mtjin.androidarchitecturestudy.utils.MyApplication


class MovieSearchActivity : AppCompatActivity(), MovieSearchContract.View {

    private lateinit var binding: ActivityMovieSearchBinding
    private lateinit var presenter: MovieSearchContract.Presenter
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var query: String
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)
        initDataBinding()
        inject()
        initAdapter()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_search)
        binding.search = this
    }

    private fun inject() {
        myApplication = application as MyApplication
        presenter = MovieSearchPresenter(this, myApplication.movieRepository)
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }
        scrollListener = object :
            EndlessRecyclerViewScrollListener(binding.rvMovies.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.requestPagingMovie(query, totalItemsCount + 1)
            }
        }
        binding.rvMovies.addOnScrollListener(scrollListener)
        binding.rvMovies.adapter = movieAdapter
    }

    fun onSearchClick() {
        query = binding.etInput.text.toString().trim()
        presenter.requestMovie(query)
    }

    override fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyQuery() {
        Toast.makeText(this, getString(R.string.search_input_query_msg), Toast.LENGTH_SHORT).show()
    }

    override fun showWait() {
        Toast.makeText(this, getString(R.string.wait_toast), Toast.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {
        Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT).show()
    }

    override fun showNoMovie() {
        Toast.makeText(this, getString(R.string.no_movie_error_msg), Toast.LENGTH_SHORT).show()
    }

    override fun showLastPage() {
        Toast.makeText(this, getString(R.string.last_page_msg), Toast.LENGTH_SHORT).show()
    }

    override fun scrollResetState() {
        scrollListener.resetState()
    }

    override fun searchMovieSuccess(movieList: List<Movie>) {
        movieAdapter.clear()
        movieAdapter.addItems(movieList)
        Toast.makeText(this, getString(R.string.load_movie_success_msg), Toast.LENGTH_SHORT).show()
    }

    override fun pagingMovieSuccess(movieList: List<Movie>) {
        movieAdapter.addItems(movieList)
        Toast.makeText(this, getString(R.string.load_movie_success_msg), Toast.LENGTH_SHORT).show()
    }


}
