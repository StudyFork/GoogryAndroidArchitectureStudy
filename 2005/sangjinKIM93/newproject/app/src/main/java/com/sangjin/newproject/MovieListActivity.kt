package com.sangjin.newproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

        movieNameInputBtn.setOnClickListener {
            getMovieList(movieNameET.text.toString())
        }

    }


    private fun getMovieList(keyWord: String){
        MovieApi.retrofitService.requestMovieList(keyword = keyWord)
            .enqueue(object: Callback<ResponseData>{
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val result = response.body()?.items

                    if (result != null) {
                        movieList = result
                        refreshList()
                    }
                    else{
                        noResultMessage()
                    }

                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                }
            })
    }

    private fun refreshList(){
        movieListView.layoutManager = linearLayoutManager
        movieListAdapter = MovieListAdapter(movieList, this)
        movieListView.adapter = movieListAdapter

        movieListView.visibility = View.VISIBLE
        guideMessageToInput.visibility = View.INVISIBLE

        //각 항목 클릭시 이벤트
        movieListAdapter.onItemClickListener = {position ->
            val intent = Intent(this, MovieDetailActiviry::class.java)
            intent.putExtra("LINK", movieList.get(position).link)
            startActivity(intent)
        }
    }

    private fun noResultMessage(){
        movieListView.visibility = View.INVISIBLE
        guideMessageToInput.visibility = View.VISIBLE

        guideMessageToInput.text = "검색 결과가 없습니다."
    }
}
