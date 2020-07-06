package com.hyper.hyapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MovieAdapter

    private val CLIENT_ID = "ImN25OL4axIIwuH6jXDj"
    private val CLIENT_SECRET = "vysJ6N1WXx"
    private val BASE_URL = "https://openapi.naver.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = MovieAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: NaverAPI = retrofit.create(NaverAPI::class.java)

        searchButton.setOnClickListener {

            if (searchText.text.toString().isNotEmpty()) {
                movieSearch(service)
            } else {
            }
        }
    }

    private fun movieSearch(service: NaverAPI) {
        service.getSearchMovie(
            clientId = CLIENT_ID,
            clientSecret = CLIENT_SECRET,
            query = searchText.text.toString()
        ).enqueue(object : Callback<ResultGetSearchMovie> {
            override fun onFailure(call: Call<ResultGetSearchMovie>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<ResultGetSearchMovie>,
                response: Response<ResultGetSearchMovie>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        viewAdapter.resetData(it.items)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "네트워크를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}


