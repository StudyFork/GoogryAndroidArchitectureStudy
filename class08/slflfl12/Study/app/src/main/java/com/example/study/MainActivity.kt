package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.study.databinding.ActivityMainBinding
import com.example.study.model.Movie
import com.example.study.model.NaverSearch
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieAdapter = MovieAdapter()
    val clientId = "AZeVMtYlsaS7bdr8W7PX"
    val clientSecret = "a7hDdCsKST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = movieAdapter
        search_button.setOnClickListener {
            fetchJson(search_editText.text.toString())


        }


    }

    fun fetchJson(title: String) {
        var text: String = URLEncoder.encode(title, "UTF-8")
        var apiURL: String =
            "https://openapi.naver.com/v1/search/movie.json?query=${text}&display=10&start=1"
        var url: URL = URL(apiURL)

        val formBody = FormBody.Builder()
            .add("query", "${text}")
            .add("display", "10")
            .add("start", "1")
            .add("genre", "1")
            .build()

        val request = Request.Builder()
            .url(url)
            .addHeader("X-Naver-Client-Id", clientId)
            .addHeader("X-Naver-Client-Secret", clientSecret)
            .method("GET", null)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("Success to execute request : $body")


                val gson = GsonBuilder().create()

                val NaverSearch = gson.fromJson(body, NaverSearch::class.java)



                runOnUiThread {
                    movieAdapter.setItem(NaverSearch.items)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

        })
    }


}




