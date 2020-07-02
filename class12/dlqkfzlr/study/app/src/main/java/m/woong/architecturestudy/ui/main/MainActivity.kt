package m.woong.architecturestudy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import m.woong.architecturestudy.R
import m.woong.architecturestudy.network.MovieApi
import m.woong.architecturestudy.network.NetworkConnectionInterceptor
import m.woong.architecturestudy.network.response.MovieResponse
import m.woong.architecturestudy.ui.adapter.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MovieAdapter
    private lateinit var service: MovieApi
    private val movieList = ArrayList<MovieResponse.Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        service = MovieApi.invoke(NetworkConnectionInterceptor(this))

        movie_search_btn.setOnClickListener {
            val keyword = movie_search_et.text
            Toast.makeText(
                this@MainActivity,
                "검색! :$keyword",
                Toast.LENGTH_SHORT
            ).show()
            if (keyword != null) {
                requestToGetMovieList(keyword.toString())
            }
        }

        viewAdapter = MovieAdapter(movieList)
        recyclerView = findViewById<RecyclerView>(R.id.movie_rv).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
    }

    /*
     * Operation: Request Movie List
     */
    private fun requestToGetMovieList(keyword: String) {
        val call = service.movieSearch(keyword)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    movieList.clear()
                    body?.let {
                        movieList.addAll(body.items)
                    }
                    viewAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "응답실패:${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}
