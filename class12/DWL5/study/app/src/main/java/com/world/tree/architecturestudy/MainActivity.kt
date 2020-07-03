package com.world.tree.architecturestudy

import android.app.PendingIntent.getService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MovieAdapter()
        recyclerView.adapter = adapter

        btnSearch.setOnClickListener {
            SearchRetrofit.retrofitService.requestSearchMovie(query = etUrl.text.toString())
                .enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        response.body()?.let {
                            adapter.setDataList(it.items)
                        }
                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "실패", Toast.LENGTH_LONG)
                            .show()
                    }
                })
        }
    }
}
