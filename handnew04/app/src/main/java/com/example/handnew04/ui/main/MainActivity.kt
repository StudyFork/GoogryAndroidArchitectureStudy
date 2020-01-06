package com.example.handnew04.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.handnew04.ui.movie.MovieDetailActivity
import com.example.handnew04.adapter.MovieRecyclerAdapter
import com.example.handnew04.R
import com.example.handnew04.data.MovieRepository
import com.example.handnew04.data.items
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {
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
        recyclerAdapter.setItemClickListener(object :
            MovieRecyclerAdapter.ItemClickListener {
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

    override fun showEmptyResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showInputLengthZero() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNotConnectedNetwork() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSuccessSearchMovie() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMovieDetailActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
