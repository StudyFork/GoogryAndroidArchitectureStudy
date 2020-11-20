package com.architecture.androidarchitecturestudy.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.adapter.MovieAdapter
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSource
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.webservice.ApiClient
import com.architecture.androidarchitecturestudy.webservice.NetworkService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var networkService: NetworkService
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private lateinit var movieAdapter: MovieAdapter
    private val presenter by lazy {
        networkService = ApiClient.NETWORK_SERVICE
        movieRemoteDataSource = MovieRemoteDataSourceImpl(networkService)
        val repositoryMovieDataImpl = MovieRepositoryImpl(movieRemoteDataSource)
        MainPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        btn_main_search.setOnClickListener {
            presenter.findMovie(et_main_search.text.toString())
        }
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        rv_main_movie.adapter = movieAdapter
    }

    override fun removeKeyboard() {
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_main_search.windowToken,
            0
        )
    }

    override fun updateMovieRecycler(items: List<Movie>) {
        movieAdapter.setItemList(items)
    }

    override fun failMovieGet(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun noQuery() {
        Toast.makeText(this, getString(R.string.query_none), Toast.LENGTH_SHORT)
            .show()
    }

    override fun noResult() {
        Toast.makeText(this, getString(R.string.result_none), Toast.LENGTH_SHORT)
            .show()
    }
}
