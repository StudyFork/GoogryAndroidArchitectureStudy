package com.onit.googlearchitecturestudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.onit.googlearchitecturestudy.MovieInformationActivity.Companion.MOVIE_URL
import com.onit.googlearchitecturestudy.data.repository.MovieRepository
import com.onit.googlearchitecturestudy.data.repository.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val movieRepositroy: MovieRepository = MovieRepositoryImpl()
    private lateinit var resultMovieListRecyclerAdapter: ResultMovieListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        searchButton.setOnClickListener {
            clickSearchButton()
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        resultMovieListRecyclerAdapter =
            ResultMovieListRecyclerAdapter { position -> clickMovieItem(position) }
        resultMovieListRecyclerView.adapter = resultMovieListRecyclerAdapter
    }

    private fun clickMovieItem(position: Int) {
        val intent = Intent(this, MovieInformationActivity::class.java).apply {
            putExtra(MOVIE_URL, resultMovieListRecyclerAdapter.getMovieURL(position))
        }
        startActivity(intent)
    }

    private fun clickSearchButton() {
        hideKeyboard()

        CoroutineScope(Dispatchers.Main).launch {
            loadingProgressBar.visibility = View.VISIBLE

            val searchKeyword = searchFieldEditText.text.toString()
            val response =
                withContext(Dispatchers.IO) { movieRepositroy.getMovieList(searchKeyword) }

            if (response.isSuccessful) {
                val movieList = response.body()?.movies
                if (movieList == null) {
                    Toast.makeText(applicationContext, "검색결과가 없습니다.", Toast.LENGTH_LONG).show()
                } else {
                    resultMovieListRecyclerAdapter.setMovieList(movieList)
                }
            } else {
                Log.e("MainActivity", "네이버 API요청에 실패했습니다. responseCode = ${response.code()}")
                Toast.makeText(applicationContext, "네트워크가 불안정합니다.", Toast.LENGTH_LONG).show()
            }

            loadingProgressBar.visibility = View.GONE
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
