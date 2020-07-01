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

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val clientId = "ImN25OL4axIIwuH6jXDj"
    private val clientSecret = "vysJ6N1WXx"
    private val baseUrl = "https://openapi.naver.com"

    var searchTitle = ""
    var item = listOf<ResultGetSearchMovie.Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = MovieAdapter(item)
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: NaverAPI = retrofit.create(NaverAPI::class.java)

        searchButton.setOnClickListener {
            searchTitle = searchText.text.toString()

            if (searchTitle == "") {
            } else {
                movieSearch(service)
            }
        }
    }

    private fun movieSearch(service: NaverAPI) {
        service.getSearchMovie(
            clientId = clientId,
            clientSecret = clientSecret,
            query = searchTitle
        ).enqueue(object : Callback<ResultGetSearchMovie> {
            override fun onFailure(call: Call<ResultGetSearchMovie>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResultGetSearchMovie>,
                response: Response<ResultGetSearchMovie>
            ) {
                item = response.body()!!.items
                recyclerView.apply {
                    adapter = MovieAdapter(item)
                    adapter?.notifyDataSetChanged()
                }
            }
        })
    }
}