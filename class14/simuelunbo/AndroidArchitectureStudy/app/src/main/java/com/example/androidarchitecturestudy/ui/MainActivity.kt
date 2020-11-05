package com.example.androidarchitecturestudy.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.api.MovieResponse
import com.example.androidarchitecturestudy.api.NaverMovieInterface
import com.example.androidarchitecturestudy.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        btn_search.setOnClickListener {
            val searchText = et_search.text.toString()
            if (searchText.isEmpty()) {
                Toast.makeText(this, "검색을 원하시는 영화 제목을 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.isVisible = true
                hideKeyboard(this)
                requestMovieInfo(searchText)
            }
        }
    }

    private fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_search.windowToken, 0)
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        rcv_result.adapter = movieAdapter
    }

    private fun requestMovieInfo(query: String) {
        NaverMovieInterface.create().searchMovies(query).enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                progressBar.isVisible = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        movieAdapter.setMovieList(it.items as ArrayList<Movie>)
                        et_search.text?.clear()
                    }
                } else {
                    Log.e("MainActivity", response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                progressBar.isVisible = false
                Toast.makeText(applicationContext, "다시 검색해 주세요", Toast.LENGTH_SHORT).show()
            }

        })
    }
}