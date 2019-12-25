package com.example.architecture_project.feature.search

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_project.R
import com.example.architecture_project.data.NaverApi
import com.example.architecture_project.feature.movie.MovieAdapter
import com.example.architecture_project.server.NaverSevicelmpl
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView = findViewById(R.id.rv_movie)
        movieAdapter = MovieAdapter()
        movieRecyclerView.adapter = movieAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this)

        btn_search.setOnClickListener {
            callMovie(et_search.text.toString())
        }
    }

    private fun callMovie(keyword: String) {
        val call: Call<NaverApi> =
            NaverSevicelmpl.service.getMovie("Ega4tpqIyDDMBsf8nb7O", "GinlKHXc11", keyword)

        call.enqueue(
            object : Callback<NaverApi> {
                override fun onFailure(call: Call<NaverApi>, t: Throwable) {
                    Log.e("error is ", t.toString())
                }

                override fun onResponse(call: Call<NaverApi>, response: Response<NaverApi>) {
                    if (response.isSuccessful) {
                        Log.e("result is ", response.toString())
                        val movie = response.body()!!
                        movieAdapter.setMovieItemList(movie.item)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}
