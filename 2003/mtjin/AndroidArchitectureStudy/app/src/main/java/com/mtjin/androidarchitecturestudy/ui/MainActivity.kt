package com.mtjin.androidarchitecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.api.ApiClient
import com.mtjin.androidarchitecturestudy.api.ApiInterface
import com.mtjin.androidarchitecturestudy.data.Movie
import com.mtjin.androidarchitecturestudy.data.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),
    MovieAdapter.ItemClickListener {

    private lateinit var etInput: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieCall: Call<MovieResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()
    }

    fun initView() {
        etInput = findViewById(R.id.et_input)
        btnSearch = findViewById(R.id.btn_search)
        rvMovies = findViewById(R.id.rv_movies)
        movieAdapter = MovieAdapter()
        rvMovies.adapter = movieAdapter
    }

    private fun initListener() {
        //어댑터 아이템 클릭리스너
        movieAdapter.setItemClickListener(this)
        //검색버튼
        btnSearch.setOnClickListener {
            val query = etInput.text.toString().trim()
            if (query.isEmpty()) {
                onToastMessage("검색어를 입력해주세요.")
            } else {
                onToastMessage("잠시만 기다려주세요.")
                requestMovie(query)
            }
        }
    }

    private fun requestMovie(query: String) {
        movieAdapter.clear()
        val apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        movieCall = apiInterface.getSearchMovie(query)
        movieCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onToastMessage("불러오는데 실패 했습니다.")
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                with(response) {
                    if (isSuccessful && body() != null) {
                        body()?.movies?.let { it -> movieAdapter.setItems(it) }
                    } else {
                        onToastMessage("불러오는데 실패 했습니다.")
                    }
                }
            }
        })
    }

    override fun onItemClick(movie: Movie) {
        Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
            it.resolveActivity(packageManager) != null
        }?.run(this::startActivity)
    }

    private fun onToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(this::movieCall.isInitialized){
            movieCall.cancel()
        }
    }
}
