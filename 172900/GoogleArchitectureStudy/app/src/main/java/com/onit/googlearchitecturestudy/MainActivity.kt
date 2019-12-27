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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

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
        resultMovieListRecyclerAdapter = ResultMovieListRecyclerAdapter(
            object : ResultMovieListRecyclerAdapter.ClickListener {
                override fun clickViewHolder(position: Int) {
                    clickMovieItem(position)
                }
            })
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

            val response =
                withContext(Dispatchers.IO) { searchMovieList(searchFieldEditText.text.toString()) }

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

    private suspend fun searchMovieList(searchWord: String): Response<Movies> {
        val client = OkHttpClient.Builder().apply {
            interceptors().add(NaverAPIInterceptor())
        }.build()
        return Retrofit.Builder().apply {
            baseUrl(Config.NAVER_API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build().create(NaverAPIService::class.java).getMovieList(
            searchWord
        )
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
