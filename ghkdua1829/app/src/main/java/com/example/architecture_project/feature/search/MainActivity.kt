package com.example.architecture_project.feature.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.feature.movie.MovieAdapter
import com.example.architecture_project.data.datasource.remote.retrofit.NaverSevicelmpl
import com.example.architecture_project.data.repository.NaverRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView   //수정완료
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var naverRepository: NaverRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        naverRepository = NaverRepository()
        movieRecyclerView = findViewById(R.id.rv_movie)
        movieAdapter = MovieAdapter(object :MovieAdapter.MovieViewHolder.ItemClickListener{
            override fun onItemClick(position: Int) {
                val goWebView = Intent(this@MainActivity, WebviewActivity::class.java)
            goWebView.putExtra(URL, movieAdapter.getMovieLink(position))
            startActivity(goWebView)
            }
        })
        movieRecyclerView.adapter = movieAdapter

        btn_search.setOnClickListener {
            callMovie(et_search.text.toString())
        }
    }

    private fun callMovie(keyword: String) {   //수정완료
        naverRepository.getMovieData(keyword,success = {movieAdapter.setMovieItemList(it.item)},fail = {Log.e("error is",it.toString())})
//        val call: Call<NaverApi> =
//            NaverSevicelmpl.service.getMovie( keyword)
//
//        call.enqueue(
//            object : Callback<NaverApi> {
//                override fun onFailure(call: Call<NaverApi>, t: Throwable) {
//                    Log.e("error is ", t.toString())
//                }
//
//                override fun onResponse(call: Call<NaverApi>, response: Response<NaverApi>) {
//                    if (response.isSuccessful) {
//                        Log.e("result is ", response.toString())
//                        val movie = response.body()!!
//                        movieAdapter.setMovieItemList(movie.item)
//                        movieAdapter.notifyDataSetChanged()
//                    }
//                }
//            }
//        )
    }
}
