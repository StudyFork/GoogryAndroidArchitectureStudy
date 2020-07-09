package com.world.tree.architecturestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.world.tree.CommonApplication
import com.world.tree.MovieContainer
import com.world.tree.architecturestudy.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
