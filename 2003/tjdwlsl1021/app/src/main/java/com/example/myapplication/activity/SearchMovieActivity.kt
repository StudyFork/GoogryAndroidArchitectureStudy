package com.example.myapplication.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.SearchMovieAdapter
import com.example.myapplication.model.RSP_SearchMovieInfo
import com.example.myapplication.model.Item_SearchMovie
import com.example.myapplication.network.RetrofitHelper
import kotlinx.android.synthetic.main.activity_search_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * 영화검색 Activity
 * */
class SearchMovieActivity : AppCompatActivity() {
    private val TAG = "SearchMovieActivity"
    private val movieAdapter =  SearchMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        rv_movie.setHasFixedSize(true)
        rv_movie.adapter = movieAdapter

        movieAdapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }

        btn_search.setOnClickListener(View.OnClickListener {

            var etMovieTitle = et_movie_title.text.toString()
            if (etMovieTitle.isNotEmpty()) {
                getMovieList(etMovieTitle)
            } else {
                Toast.makeText(this, R.string.activity_toast_empty_movie_title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getMovieList(query: String?) {
        val call = RetrofitHelper.retrofitService.getMovieList(query, 10, 1, "1")

        call?.enqueue(object : Callback<RSP_SearchMovieInfo?> {
            override fun onResponse(call: Call<RSP_SearchMovieInfo?>, response: Response<RSP_SearchMovieInfo?>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    if (null != result) {
                        val movies: ArrayList<Item_SearchMovie> = ArrayList(result.items)

                        movieAdapter.addItems(movies)
                        rv_movie!!.adapter = movieAdapter
                    }
                }
            }

            override fun onFailure(call: Call<RSP_SearchMovieInfo?>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }
}