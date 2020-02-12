package com.example.study.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil.setContentView
import com.example.study.R
import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepositoryImpl
import com.example.study.data.source.local.NaverSearchLocalDataSourceImpl
import com.example.study.data.source.local.SearchResultDatabase
import com.example.study.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.databinding.ActivityMainBinding
import com.example.study.ui.adapter.MovieAdapter
import com.example.study.ui.detail.DetailActivity
import com.example.study.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val vm: MainViewModel by lazy {
        MainViewModel(
            NaverSearchRepositoryImpl.getInstance(
                NaverSearchLocalDataSourceImpl.getInstance(SearchResultDatabase.getInstance(this)!!.searchResultDao())
                , NaverSearchRemoteDataSourceImpl.getInstance()
            )
        )
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter{ link ->
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_URL, link.toString())
            this.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.rvMovieList.adapter = movieAdapter
        binding.activity = this@MainActivity

        getRecentSearchResult()


    }

    fun getRecentSearchResult() {
        presenter.getRecentSearchResult()
    }

    fun getMovieList(query: String) {
        if (query.isNullOrBlank()) {
            showErrorQueryEmpty()
        } else {
            presenter.getMovies(query)
        }
    }

    override fun updateMovieList(items: List<Movie>) {
        movieAdapter.setItem(items)
    }

    override fun showProgress() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.pbLoading.visibility = View.GONE
    }

    override fun showErrorQueryEmpty() {
        Toast.makeText(this@MainActivity, R.string.empty_query_message, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorEmptyResult() {
        Toast.makeText(this@MainActivity, R.string.empty_result_message, Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
            hideSoftInputFromWindow(binding.etMovieSearch.windowToken, 0)
        }
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}




