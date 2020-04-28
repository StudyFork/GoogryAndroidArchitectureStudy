package com.sangjin.newproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sangjin.newproject.adapter.Movie
import com.sangjin.newproject.adapter.MovieListAdapter
import com.sangjin.newproject.adapter.ResponseData
import kotlinx.android.synthetic.main.activity_movie_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListActivity : AppCompatActivity() {

    private var movieList = listOf<Movie>()
    private lateinit var movieListAdapter: MovieListAdapter
    private val linearLayoutManager = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

    }


    private fun getMovieList(keyWord: String){
        MovieApi.retrofitService.requestMovieList(keyword = keyWord)
            .enqueue(object: Callback<ResponseData>{
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val result = response.body()?.items
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                }
            })
    }

    private fun refreshList(){
        movieListView.layoutManager = linearLayoutManager
        movieListAdapter = MovieListAdapter(movieList)
        movieListView.adapter = movieListAdapter
    }
}
