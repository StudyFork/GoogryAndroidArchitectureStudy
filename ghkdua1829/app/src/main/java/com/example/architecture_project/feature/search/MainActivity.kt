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

    private lateinit var MovieRecyclerView: RecyclerView
    private lateinit var MovieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            Call_Movie(et_search.text.toString())
        }
    }

    fun Call_Movie(keyword: String) {
        MovieRecyclerView = findViewById(R.id.rv_movie)
        MovieAdapter = MovieAdapter(this)
        MovieRecyclerView.adapter = MovieAdapter
        MovieRecyclerView.layoutManager = LinearLayoutManager(this)


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
                        MovieAdapter.data = movie.item
                        MovieAdapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}
