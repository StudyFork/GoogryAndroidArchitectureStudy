package com.hyper.hyapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val CLIENT_ID = "ImN25OL4axIIwuH6jXDj"
    private val CLIENT_SECRET = "vysJ6N1WXx"
    private val BASE_URL = "https://openapi.naver.com"

    var searchTitle = ""
    var item = listOf<ResultGetSearchMovie.Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = MovieAdapter(item)
        viewManager = LinearLayoutManager(this)
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: NaverAPI = retrofit.create(NaverAPI::class.java)

        searchButton.setOnClickListener {
            searchTitle = searchText.text.toString()

            if (searchTitle.isNotEmpty()) {
                movieSearch(service)
            } else {
            }
        }
    }

    private fun movieSearch(service: NaverAPI) {
        service.getSearchMovie(
            clientId = CLIENT_ID,
            clientSecret = CLIENT_SECRET,
            query = searchTitle
        ).enqueue(object : Callback<ResultGetSearchMovie> {
            override fun onFailure(call: Call<ResultGetSearchMovie>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResultGetSearchMovie>,
                response: Response<ResultGetSearchMovie>
            ) {
                item = response.body()!!.items

                item?.let {
                    recyclerView.apply {
                        adapter = MovieAdapter(item)
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}