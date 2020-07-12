package com.world.tree.architecturestudy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.world.tree.architecturestudy.CommonApplication
import com.world.tree.architecturestudy.MovieContainer
import com.world.tree.architecturestudy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var  movieContainer: MovieContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieContainer = (application as CommonApplication).movieContainer
        val adapter = MovieAdapter()
        recyclerView.adapter = adapter

        btnSearch.setOnClickListener {
            adapter.clearData()
            movieContainer.repository.getMovies(etUrl.text.toString(), {
              adapter.addData(it)
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })
        }
    }
}
