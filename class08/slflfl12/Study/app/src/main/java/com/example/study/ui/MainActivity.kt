package com.example.study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study.R
import com.example.study.data.datasource.remote.network.NaverApiService
import com.example.study.data.model.Movie
import com.example.study.data.model.NaverSearch
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private var movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = movieAdapter

        search_button.setOnClickListener {
            getMovieList(search_editText.text.toString())
        }

        movieAdapter.setOnItemClickListener { movie ->
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("url", movie.link.toString())

            this.startActivity(intent)
        }
    }

    private fun getMovieList(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(NaverApiService::class.java).apply {
            this.getMovieList("AZeVMtYlsaS7bdr8W7PX", "a7hDdCsKST", query)
                .enqueue(object : Callback<NaverSearch> {

                    override fun onFailure(call: Call<NaverSearch>, t: Throwable) {
                        println("network error")
                    }

                    override fun onResponse(
                        call: Call<NaverSearch>,
                        response: Response<NaverSearch>
                    ) {
                        if (response.isSuccessful) {
                            println("성공")
                            movieAdapter.setItem(response.body()?.items as List<Movie>)
                        }

                    }
                })
        }
    }
}




