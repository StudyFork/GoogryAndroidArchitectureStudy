package com.example.myproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.retrofit.RetrofitClient
import com.example.myproject.retrofit.model.Items
import com.example.myproject.retrofit.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val movies: ArrayList<Items> = ArrayList()
    val movieAdapter = MovieAdapter(this, movies)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        btn_search.setOnClickListener {
            val title = edit_title.text.toString()
            if (title.isEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                recyclerview.layoutManager?.scrollToPosition(0);
                getMovie(title);
            }
        }
    }

    fun setRecyclerView() {
        recyclerview.apply {
            recyclerview.setHasFixedSize(true)
            recyclerview.layoutManager = LinearLayoutManager(context)
            recyclerview.setAdapter(movieAdapter)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))
        }
    }

    fun getMovie(title: String) {
        RetrofitClient.getService().getMovies(title).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("sangmin_error", t.message)
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val movies = ArrayList<Items>(response.body()!!.items)
                    if (movies.size == 0) {
                        movieAdapter.clearItems()
                        Toast.makeText(applicationContext, "$title 를 찾을 수 없습니다", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        movieAdapter.clearAndAddItems(movies)
                    }
                } else {
                    Log.e("sangmin_error", response.message())
                }
            }
        })
    }

}