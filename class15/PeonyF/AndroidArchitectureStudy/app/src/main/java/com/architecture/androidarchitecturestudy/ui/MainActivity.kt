package com.architecture.androidarchitecturestudy.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.adapter.MovieAdapter
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val movieRepository by lazy { MovieRepositoryImpl() }
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        btn_main_search.setOnClickListener {
            if (et_main_search.text.isEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                searchMovie(et_main_search.text.toString())
                removeKeyboard()
            }
        }
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        rv_main_movie.adapter = movieAdapter
    }

    private fun searchMovie(keyword: String) {
        movieRepository.getMovieData(keyword, 30,
            onSuccess = { movieAdapter.setItemList(it.items as ArrayList<Movie>) },
            onFailure = { Log.e("Api is fail", it.toString()) })
    }

    private fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_main_search.windowToken, 0
        )
}
