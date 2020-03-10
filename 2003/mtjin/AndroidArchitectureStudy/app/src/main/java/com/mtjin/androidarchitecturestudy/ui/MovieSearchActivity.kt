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
import com.mtjin.androidarchitecturestudy.data.source.MovieRepository
import com.mtjin.androidarchitecturestudy.data.source.MovieRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.source.local.MovieDao
import com.mtjin.androidarchitecturestudy.data.source.local.MovieDatabase
import com.mtjin.androidarchitecturestudy.data.source.local.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.source.local.MovieLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.data.source.remote.MovieRemoteDataSource
import com.mtjin.androidarchitecturestudy.data.source.remote.MovieRemoteDataSourceImpl


class MovieSearchActivity : AppCompatActivity() {

    private lateinit var etInput: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private lateinit var movieLocalDataSource: MovieLocalDataSource
    private lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        initView()
        initListener()
    }

    private fun initView() {
        etInput = findViewById(R.id.et_input)
        btnSearch = findViewById(R.id.btn_search)
        rvMovies = findViewById(R.id.rv_movies)
        movieAdapter = MovieAdapter()
        rvMovies.adapter = movieAdapter

        movieDao = MovieDatabase.getInstance(this).movieDao()
        movieRemoteDataSource = MovieRemoteDataSourceImpl()
        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }

    private fun initListener() {
        //어댑터 아이템 클릭리스너
        movieAdapter.setItemClickListener { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }

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
        movieRepository.getSearchMovies(query,
            success = {
                movieAdapter.clear()
                movieAdapter.setItems(it)
                if (it.isEmpty()) {
                    onToastMessage("영화를 불러왔습니다.")
                } else {
                    onToastMessage("해당 영화는 존재하지 않습니다.")
                }
            },
            fail = {
                onToastMessage("불러오는데 실패 했습니다.")
            })
    }

    private fun onToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
