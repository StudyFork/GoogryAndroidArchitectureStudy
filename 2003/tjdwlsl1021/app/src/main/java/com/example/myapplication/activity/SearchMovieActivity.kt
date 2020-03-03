package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.SearchMovieAdapter
import com.example.myapplication.item.SearchMovieInfo
import com.example.myapplication.network.RetrofitHelper
import com.example.myapplication.network.RetrofitHelper.RequestNaverApi
import kotlinx.android.synthetic.main.activity_search_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMovieActivity : AppCompatActivity() {
    private val TAG = "SearchMovieActivity"

    private var movieAdapter: SearchMovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        rv_movie.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_movie.setLayoutManager(mLayoutManager)

        btn_search.setOnClickListener(View.OnClickListener { getMovieList(et_movie_title.getText().toString()) })
    }


    fun getMovieList(query: String?) {
        val requestNaverApi = RetrofitHelper.retrofit?.create(
            RequestNaverApi::class.java
        )
        val call = requestNaverApi?.getMovieList(query, 10, 1, "1")
        call?.enqueue(object : Callback<SearchMovieInfo?> {
            override fun onResponse(
                call: Call<SearchMovieInfo?>,
                response: Response<SearchMovieInfo?>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (null != result) {
                        movieAdapter =
                            SearchMovieAdapter(this@SearchMovieActivity, result.items)
                        rv_movie!!.adapter = movieAdapter
                    }
                }
            }

            override fun onFailure(
                call: Call<SearchMovieInfo?>,
                t: Throwable
            ) {
                Log.e(TAG, t.message)
            }
        })
    }
}