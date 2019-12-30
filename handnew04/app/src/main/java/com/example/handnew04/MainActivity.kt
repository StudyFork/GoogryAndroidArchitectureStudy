package com.example.handnew04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.handnew04.data.MovieRepository
import com.example.handnew04.data.items
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: MovieRecyclerAdapter
    lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initailize()
        setEventHandler()
    }


    private fun initailize() {
        movieRepository = MovieRepository()

        recyclerAdapter = MovieRecyclerAdapter()
        recyclerAdapter.setItemClickListener(object : MovieRecyclerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val nextIntent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                nextIntent.putExtra(
                    getString(R.string.movieLink),
                    recyclerAdapter.getMovieLink(position)
                )
                startActivity(nextIntent)
            }
        })

        rcv_movies.adapter = recyclerAdapter
    }

    private fun setEventHandler() {
        btn_searchButton.setOnClickListener {
            val inputText = et_searchBar.text.toString()
            if (inputText.isNotEmpty()) searchMovie(inputText)
        }
    }


    private fun searchMovie(inputText: String) {
        movieRepository.getMovieData(inputText
            , success = { recyclerAdapter.setItemList(it.items as ArrayList<items>) },
            fail = { Log.e("NaverMovieApi Fail ", it.message) })
    }
}
