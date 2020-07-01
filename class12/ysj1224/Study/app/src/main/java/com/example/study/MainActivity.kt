package com.example.study

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val clientId = "hDyUQTbovi0BszAf5h87"
const val clientSecret = "3FsmSYBKbJ"
const val baseUrl = "https://openapi.naver.com"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    var item = listOf<NaverApiData.Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerAdapter(item)
        recyclerView = findViewById<RecyclerView>(R.id.rv_main).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: NaverApiInterface = retrofit.create(NaverApiInterface::class.java)

        btn_check.setOnClickListener {
            if (etv_search.text.isEmpty()) {
                Log.d("search","${etv_search.text}")
                Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("search","${etv_search.text}")
                doSearch(service)
            }

        }
    }

    private fun doSearch(service: NaverApiInterface) {
        service.getSearch(
            clientId = clientId,
            clientPw = clientSecret,
            query = etv_search.text.toString()
        ).enqueue(object : Callback<NaverApiData> {
            override fun onFailure(call: Call<NaverApiData>, t: Throwable) {
            }

            override fun onResponse(call: Call<NaverApiData>, response: Response<NaverApiData>) {
                if (response.isSuccessful) {
                    item = response.body()!!.items
                    recyclerView.adapter = RecyclerAdapter(item)
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "네트워크를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}



