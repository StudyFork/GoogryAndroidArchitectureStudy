package com.mtjin.androidarchitecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.movie_search.Movie
import com.mtjin.androidarchitecturestudy.utils.MyApplication


class MovieSearchActivity : AppCompatActivity(), MovieSearchContract.View {

    private lateinit var presenter: MovieSearchContract.Presenter
    private lateinit var etInput: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var pbLoading: ProgressBar
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var query: String
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        initView()
        initAdapter()
        initListener()
        presenter = MovieSearchPresenter(this, myApplication.movieRepository)
    }

    private fun initView() {
        myApplication = application as MyApplication
        etInput = findViewById(R.id.et_input)
        btnSearch = findViewById(R.id.btn_search)
        rvMovies = findViewById(R.id.rv_movies)
        pbLoading = findViewById(R.id.pb_loading)
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        rvMovies.layoutManager = linearLayoutManager
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.requestPagingMovie(query, totalItemsCount + 1)
            }
        }
        rvMovies.addOnScrollListener(scrollListener)
        rvMovies.adapter = movieAdapter
    }

    private fun initListener() {
        //어댑터 아이템 클릭리스너
        movieAdapter.setItemClickListener { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }
        //검색버튼
        btnSearch.setOnClickListener {
            query = etInput.text.toString().trim()
            presenter.requestMovie(query)
        }
    }

    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoading.visibility = View.GONE
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
        movieAdapter.setItems(movieList)
        Toast.makeText(this, getString(R.string.load_movie_success_msg), Toast.LENGTH_SHORT).show()
    }

    override fun pagingMovieSuccess(movieList: List<Movie>) {
        movieAdapter.setItems(movieList)
        Toast.makeText(this, getString(R.string.load_movie_success_msg), Toast.LENGTH_SHORT).show()
    }


}
