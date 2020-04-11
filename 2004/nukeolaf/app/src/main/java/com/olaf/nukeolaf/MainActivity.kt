package com.olaf.nukeolaf

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val retrofitClient: RetrofitInterface = RetrofitClient.client
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_rv.adapter = movieAdapter

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMovie(query)
                } else {
                    makeToast("검색어를 입력해주세요")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchMovie(query: String) {
        retrofitClient.searchMovie(
            query, 10, 1
        ).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val body = response.body()
                if (body != null && response.isSuccessful) {
                    val movies = body.items
                    if (movies.isNotEmpty()) {
                        showMovies(movies)
                    } else {
                        makeToast("${query}에 대한 검색결과가 없습니다")
                    }
                } else {
                    makeToast("서버 에러 : 서버에 문제가 있습니다")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                makeToast("네트워크 에러 : 인터넷 연결을 확인해 주세요")
                Log.d("searchMovie", t.toString())
            }
        })
    }

    private fun showMovies(movies: List<MovieItem>) {
        movieAdapter.setMovies(movies)
    }

    private fun makeToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
