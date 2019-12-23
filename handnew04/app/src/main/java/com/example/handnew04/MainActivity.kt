package com.example.handnew04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var recycler_Adapter: RecyclerAdapter_Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initailize()
        setEventHandler()
    }


    private fun initailize() {
        recycler_Adapter = RecyclerAdapter_Movie(this)
        recycler_Adapter.setItemClickListener(object : RecyclerAdapter_Movie.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val nextIntent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                nextIntent.putExtra(
                    getString(R.string.movieLink),
                    recycler_Adapter.getMovieLink(position)
                )
                startActivity(nextIntent)
            }
        })


        rcv_movies.adapter = recycler_Adapter
        rcv_movies.layoutManager = LinearLayoutManager(this)
    }

    private fun setEventHandler() {
        btn_searchButton.setOnClickListener {
            val inputText = et_searchBar.text.toString()
            if (inputText.isNotEmpty()) searchMovie(inputText)
        }
    }

    private fun searchMovie(inputText: String) {
        val URL = "https://openapi.naver.com"
        val searchLimit = 100

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.requestSearchMovie(inputText, searchLimit)
            .enqueue(object : Callback<NaverMovie_Response> {
                override fun onFailure(call: Call<NaverMovie_Response>, t: Throwable) {
                    Log.i("error", t.message)
                }

                override fun onResponse(
                    call: Call<NaverMovie_Response>,
                    response: Response<NaverMovie_Response>
                ) {
                    if (response.isSuccessful) {
                        val movieResponse = response.body()
                        recycler_Adapter.setItemList(movieResponse?.items as ArrayList<items>)
                    }
                }

            })
    }
}
