package com.architecture.androidarchitecturestudy.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.adapter.MovieAdapter
import com.architecture.androidarchitecturestudy.model.MovieRepository
import com.architecture.androidarchitecturestudy.model.MovieResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val movieRepository: MovieRepository = MovieRepository()
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
        val call = movieRepository.getMovieData(keyword, 30)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("에러발생", t.toString())
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {

                    val movieResponse: MovieResponse? = response.body()
                    movieResponse?.items?.let { movieAdapter.setItemList(it) }
                }
            }
        })
    }

    private fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_main_search.windowToken, 0
        )
}
