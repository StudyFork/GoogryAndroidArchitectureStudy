package com.architecture.androidarchitecturestudy.ui.main

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.adapter.MovieAdapter
import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.databinding.ActivityMainBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.ui.searchhistory.SearchHistoryActivity
import com.architecture.androidarchitecturestudy.webservice.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var mainBinding: ActivityMainBinding

    private val mainPresenter by lazy {
        val repositoryMovieDataImpl =
            MovieRepositoryImpl(
                MovieRemoteDataSourceImpl(ApiClient.NETWORK_SERVICE),
                MovieLocalDataSourceImpl()
            )
        MainPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.mainActivity = this
        initRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SEARCH_HISTORY && resultCode == RESULT_OK) {
            data?.getStringExtra(MOVIE_KEYWORD)?.let { keyword ->
                mainPresenter.findMovie(keyword)
                mainBinding.etMainSearch.setText(keyword)
            }
        }
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        mainBinding.rvMainMovie.adapter = movieAdapter
    }

    fun findMovie() {
        mainPresenter.findMovie(mainBinding.etMainSearch.text.toString())
        mainPresenter.setSearchHistory(mainBinding.etMainSearch.text.toString())
    }

    override fun removeKeyboard() {
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_main_search.windowToken,
            0
        )
    }

    override fun updateMovieRecycler(items: List<Movie>) {
        movieAdapter.setItems(items)
    }

    override fun failMovieGet(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun searchHistory() {
        val intent = Intent(this, SearchHistoryActivity::class.java)
        startActivityForResult(intent, REQ_CODE_SEARCH_HISTORY)
    }

    companion object {
        const val REQ_CODE_SEARCH_HISTORY = 101
        const val MOVIE_KEYWORD = "movie_keyword"
    }
}