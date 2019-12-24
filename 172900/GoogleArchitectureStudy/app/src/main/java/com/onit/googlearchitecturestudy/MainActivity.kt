package com.onit.googlearchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            ArrayList(),
            object : ResultMovieListRecyclerAdapter.MovieViewHolder.ClickListener {
                override fun clickViewHolder(position: Int) {
                    clickMovieItem(position)
                }
            })

        with(resultMovieListRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = resultMovieListRecyclerAdapter
        }
    }

    private fun clickMovieItem(position: Int) {
        val intent = Intent(this, MovieInformationActivity::class.java).apply {
            putExtra("movieURL", resultMovieListRecyclerAdapter.getMovieURL(position))
        }
        startActivity(intent)
    }

    private fun clickSearchButton() {
        CoroutineScope(Dispatchers.Main).launch {
            val response =
                withContext(Dispatchers.IO) { searchMovieList(searchFieldEditText.text.toString()) }

            if (response.isSuccessful) {
                val movieList = response.body()?.movies
                if (movieList == null) {
                    Toast.makeText(applicationContext, "검색결과가 없습니다.", Toast.LENGTH_LONG).show()
                    return@launch
                }
                resultMovieListRecyclerAdapter.setMovieList(movieList)
            } else {
                Log.e("MainActivity", "네이버 API요청에 실패했습니다. responseCode = ${response.code()}")
                Toast.makeText(applicationContext, "네트워크가 불안정합니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private suspend fun searchMovieList(searchWord: String): Response<Movies> {
        return Retrofit.Builder().apply {
            baseUrl(Config.NAVER_API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }.build().create(NaverAPIService::class.java).getMovieList(
            Config.NAVER_API_CLIENT_ID,
            Config.NAVER_API_CLIENT_SECRET,
            searchWord
        ).await()
    }
}
