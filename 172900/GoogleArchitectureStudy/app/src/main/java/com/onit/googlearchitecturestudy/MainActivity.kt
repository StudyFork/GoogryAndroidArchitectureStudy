package com.onit.googlearchitecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(resultMovieListRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = ResultMovieListRecyclerAdapter(
                ArrayList(),
                object : ResultMovieListRecyclerAdapter.MovieViewHolder.ClickListener {
                    override fun clickViewHolder(position: Int) {

                    }
                })
        }
    }

}
