package com.example.study

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val clientId = "hDyUQTbovi0BszAf5h87"
const val clientSecret = "3FsmSYBKbJ"
const val baseUrl = "https://openapi.naver.com"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = RecyclerAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.rv_main).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }



        btn_check.setOnClickListener {
            if (etv_search.text.isEmpty()) {
                Log.d("search", "${etv_search.text}")
                Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("search", "${etv_search.text}")
                doSearch()
            }

        }
    }

    private fun doSearch() {
        NaverRetrofit.service.getSearch(
            clientId = clientId,
            clientPw = clientSecret,
            query = etv_search.text.toString()
        ).enqueue(object : Callback<NaverApiData> {
            override fun onFailure(call: Call<NaverApiData>, t: Throwable) {
            }

            override fun onResponse(call: Call<NaverApiData>, response: Response<NaverApiData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        viewAdapter.setItem(it.items)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "네트워크를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}



