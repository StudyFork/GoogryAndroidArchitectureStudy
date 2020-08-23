package com.hjhan.hyejeong

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.network.ApiInterface
import com.hjhan.hyejeong.network.ServiceClient
import com.hjhan.hyejeong.network.data.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.search_edit_text)
        button = findViewById(R.id.search_button)
        recyclerView = findViewById(R.id.movie_recycler_view)

        movieAdapter = MovieAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }


        button.setOnClickListener {

            val title = editText.text.toString()

            if (title.isBlank()) {
                Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()

            } else {
                requestMovieList(title)
            }
        }


    }

    private fun requestMovieList(title: String) {
        val serviceClient = ServiceClient()
        val api = serviceClient.createService(ApiInterface::class.java)
        api.getMovies(title, 10, 100).enqueue(object : Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful) {

                    response.body()?.let {

                        movieAdapter.setMovieList(it.items)
                    }

                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {

            }
        })
    }
}
