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
import m.woong.architecturestudy.ui.item.MovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val movieList = ArrayList<MovieItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_search_btn.setOnClickListener {
            var keyword = movie_search_et.text
            Toast.makeText(
                this@MainActivity,
                "검색! :$keyword",
                Toast.LENGTH_SHORT
            ).show()
            if (keyword != null) {
                requestToGetMovieList(keyword.toString())
            }
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = MovieAdapter(movieList)
        recyclerView = findViewById<RecyclerView>(R.id.movie_rv).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /*
     * Operation: Request Movie List
     */
    private fun requestToGetMovieList(keyword: String) {
        val service = MovieApi.invoke(NetworkConnectionInterceptor(this))
        val call = service.movieSearch(keyword)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    movieList.clear()
                    for (item in body!!.items) {
                        movieList.add(
                            MovieItem(
                                item.title,
                                item.link,
                                item.image,
                                item.subtitle,
                                item.pubDate,
                                item.director,
                                item.actor,
                                item.userRating
                            )
                        )
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
