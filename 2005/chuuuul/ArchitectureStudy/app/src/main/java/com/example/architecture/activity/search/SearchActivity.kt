package com.example.architecture.activity.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.architecture.R
import com.example.architecture.retrofit.MovieSearchService
import com.example.architecture.vo.MovieResponseVO
import com.example.architecture.vo.MovieVO
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity(), Callback<MovieResponseVO> {

    private val MOVIE_SEARCH_API_URL = "https://openapi.naver.com"
    private var movies = ArrayList<MovieVO>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(MOVIE_SEARCH_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setRecyclerview()
        setViewEvent()
    }



    private fun setViewEvent() {
        btn_search_searchButton.setOnClickListener {
            searchMovie()
        }

        // * 키보드 엔터 클릭 시 검색
        et_search_searchName.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                btn_search_searchButton.callOnClick()
                true
            } else
                false

        }
    }

    // * 검색
    
    private fun searchMovie() {
        val keyWord = et_search_searchName.text.toString()

        if (isValidKeyword(keyWord)) {
            val service: MovieSearchService = retrofit.create(MovieSearchService::class.java)
            val call = service.requestSearchMovie(keyWord)
            call.enqueue(this)
        }
    }

    private fun isValidKeyword(keyword: String): Boolean {
        var isValid = true
        var errorString = ""

        if (keyword.isEmpty()) {
            isValid = false
            errorString = "검색어를 입력하세요"
        }

        if (!isValid)
            Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()

        return isValid
    }

    // * RecyclerView

    private fun setRecyclerview() {

        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        recyclerview_search_movieList.adapter = MovieListAdapter(movies)
        recyclerview_search_movieList.layoutManager = gridLayoutManager
    }

    // * Retrofit Response

    override fun onResponse(call: Call<MovieResponseVO>, response: Response<MovieResponseVO>) {

        val result = response.body()

        if (result != null) {

            if (result.total > 0) {
                movies.clear()
                movies.addAll(response.body()?.movies!!)
                recyclerview_search_movieList.adapter?.notifyDataSetChanged()
            } else {
                movies.clear()
                recyclerview_search_movieList.adapter?.notifyDataSetChanged()
                Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onFailure(call: Call<MovieResponseVO>, t: Throwable) {
        Log.d("chul", "$t")
    }
}
