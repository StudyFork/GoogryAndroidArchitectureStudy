package com.example.study.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.RecyclerAdapter
import com.example.study.data.repository.MovieListRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

const val clientId = "hDyUQTbovi0BszAf5h87"
const val clientSecret = "3FsmSYBKbJ"
const val baseUrl = "https://openapi.naver.com"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerAdapter
    private val moviList = MovieListRepositoryImpl()

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
                Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                moviList.doSearch(
                    etv_search.text.toString(),
                    response = { viewAdapter.setItem(it) },
                    fail = { Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show() })

            }
        }
    }
}



