package com.hansung.firstproject

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder


class MainActivity : AppCompatActivity() {

    private val clientId = "nIzAnUTyE8HlkA34saCR" // naver 검색API 사용을 위한 Client ID
    private val clientSecret = "SVd8sHWiui" //naver 검색API 사용을 위한 Client Secret

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {

            // 입력값이 없을 때 처리
            if (editText_search.text.isEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT)
                //return@setOnClickListener
            } else {
                //레이아웃매니저 설정
                recycler_view_movies.layoutManager =
                    LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                recycler_view_movies.setHasFixedSize(true)
                // movie 항목별 구분선 추가
                recycler_view_movies.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                    )
                )

                fetchJson(editText_search.text.toString())

                //키보드 제거
                val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText_search.windowToken, 0)
            }
        }
    }

    //OkHttp3로 요청
    private fun fetchJson(vararg p0: String) {

        val text = URLEncoder.encode(p0[0], "UTF-8")
        val url =
            URL("https://openapi.naver.com/v1/search/movie.json?query=${text}&display=10&start=1&genre=")

        val request = Request.Builder()
            .url(url)
            .addHeader("X-Naver-Client-Id", clientId)
            .addHeader("X-Naver-Client-Secret", clientSecret)
            .method("GET", null)
            .build()


        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println("Success to execute request : $body")

                //Gson을 Kotlin에서 사용 가능한 object로 만든다.
                val gson = GsonBuilder().create()
                val movies = gson.fromJson(body, Movies::class.java)

                //어답터를 연결
                runOnUiThread {
                    recycler_view_movies.adapter = RecyclerViewAdapter(movies)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }
}