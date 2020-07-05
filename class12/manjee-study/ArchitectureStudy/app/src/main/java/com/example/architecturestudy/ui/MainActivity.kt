package com.example.architecturestudy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieMeta
import com.example.architecturestudy.data.repository.MovieRespositoryImpl
import com.example.architecturestudy.data.source.remote.RemoteCallback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val movieRepository = MovieRespositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val movieAdapter = MovieAdapter()

        movieRecyclerView.adapter = movieAdapter

        searchButton.setOnClickListener {
            searchEditText.text?.toString()?.let { title ->
                movieRepository.remoteSearchMovie(title, object : RemoteCallback {
                    override fun onFailure(call: Call<MovieMeta>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<MovieMeta>,
                        response: Response<MovieMeta>
                    ) {
                        val metaData = response.body()
                        metaData?.let {
                            movieAdapter.setData(movieList = it.items)
                        }
                    }

                })
            }
        }
    }
}