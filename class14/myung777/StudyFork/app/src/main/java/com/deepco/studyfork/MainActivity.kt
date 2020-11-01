package com.deepco.studyfork

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var movieList = ArrayList<Item>()
    private lateinit var api: RetrofitService
    private lateinit var recyclerAdapterMovie: RecyclerAdapterMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = RetrofitService.create()
        setRecyclerView()
        search_btn.setOnClickListener {
            val text = movie_edit_text.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "영화 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                searchMovie(text)
            }
        }
    }

    private fun searchMovie(name: String) {
        api.getSearchNews(name).enqueue(object : Callback<MovieData> {
            override fun onResponse(
                call: Call<MovieData>,
                response: Response<MovieData>
            ) {
                val movieData: MovieData? = response.body()
                movieList = movieData?.items as ArrayList<Item>
                recyclerAdapterMovie.setItemList(movieList)
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Toast.makeText(applicationContext, "다시 검색해 주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setRecyclerView() {
        recyclerAdapterMovie = RecyclerAdapterMovie()
        recycler_view.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapterMovie
        }
    }
}