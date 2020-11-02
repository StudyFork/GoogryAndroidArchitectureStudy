package com.hhi.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val recyclerAdapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        main_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        main_recyclerview.setHasFixedSize(false)
        main_recyclerview.adapter = recyclerAdapter
        main_btn_search.setOnClickListener {
            if (main_edit_search.text.isEmpty()) {
                return@setOnClickListener
            } else {

            }
        }
    }
}