package com.onit.googlearchitecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

                }
            })

        with(resultMovieListRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = resultMovieListRecyclerAdapter
        }
    }

    private fun clickSearchButton() {
        CoroutineScope(Dispatchers.Main).launch {
            val movieList =
                withContext(Dispatchers.IO) { searchMovieList(searchFieldEditText.text.toString()) }
            resultMovieListRecyclerAdapter.setMovieList(movieList)
        }
    }

    private suspend fun searchMovieList(searchWord: String): List<Movie> {
        return Retrofit.Builder().apply {
            baseUrl(Config.NAVER_API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }.build().create(NaverAPIService::class.java).getMovieList(
            Config.NAVER_API_CLIENT_ID,
            Config.NAVER_API_CLIENT_SECRET,
            searchWord
        ).await().movies
    }
}
