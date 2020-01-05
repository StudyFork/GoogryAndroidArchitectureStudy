package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val clientId = ""
    val cliendPw = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_movie_search.setOnClickListener {
            findMovie(et_movie.text.toString())
        }
    }

    private fun findMovie(query: String)
    {
        ApiClient.getService().searchMovie(query)
    }
}

