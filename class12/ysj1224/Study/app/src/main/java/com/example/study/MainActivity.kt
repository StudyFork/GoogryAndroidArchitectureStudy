package com.example.study

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val clientId = "hDyUQTbovi0BszAf5h87"
    private val clientSecret = "3FsmSYBKbJ"
    private val baseUrl = "https://openapi.naver.com"
    var searchTitle = ""
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

        etv_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchTitle = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btn_check.setOnClickListener {
            if (searchTitle == "") {
                Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                doSearch(service)
            }

        }
    }

    private fun doSearch(service: NaverApiInterface) {
        service.getSearch(
            clientId = clientId,
            clientPw = clientSecret,
            query = searchTitle
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



