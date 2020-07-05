package com.world.tree.architecturestudy

import com.world.tree.architecturestudy.Movie.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            adapter.clearData()
            SearchRetrofit.retrofitService.requestSearchMovie(query = etUrl.text.toString())
                .enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        response.body()?.let {
                            adapter.addData(it.items)
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
