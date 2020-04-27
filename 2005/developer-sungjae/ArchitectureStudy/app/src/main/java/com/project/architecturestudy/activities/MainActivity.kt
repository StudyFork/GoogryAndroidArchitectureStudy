package com.project.architecturestudy.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.architecturestudy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_search.setOnClickListener {
            if (et_search.text.toString().isEmpty()) return@setOnClickListener


            fetchJson(et_search.toString())
        }

    }
    private fun fetchJson(keyWord: String) {

    }
}
