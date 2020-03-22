package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.data.repository.MovieRepositoryDataSet
import com.example.myapplication.databinding.ActivitySearchMovieBinding

class SearchMovieActivity : AppCompatActivity(), SearchMovieContract.View {
    private val TAG = "SearchMovieActivity"

    private lateinit var movieRepositoryDataSet: MovieRepositoryDataSet
    private lateinit var presenter: SearchMoviePresenter

    private val movieAdapter = SearchMovieAdapter()

    private lateinit var binding: ActivitySearchMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_movie)

        initView()
        setOnclickListener()
        presenter = SearchMoviePresenter(this, movieRepositoryDataSet)
    }

    private fun initView() {
        movieRepositoryDataSet = application as MovieRepositoryDataSet
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter = movieAdapter
    }

    private fun setOnclickListener() {
        // 영화 검색
        binding.btnSearch.setOnClickListener(View.OnClickListener {
            val movieTitle = binding.etMovieTitle.text.toString()
            presenter.searchMovie(movieTitle)
        })

        // 영화 이미지 클릭시 이동 로직
        movieAdapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }
    }

    /**
     * View - 화면 출력
     * */
    // 영화제목 입력하세요 문구
    override fun showToastMovieTitleIsEmpty() {
        Toast.makeText(this, R.string.activity_toast_empty_movie_title, Toast.LENGTH_SHORT).show()
    }

    // 영화 리스트 어댑터에 아이템 추가 반영
    override fun addItems(it: List<MovieEntity>) {
        movieAdapter.addItems(it)
    }

    override fun recordLog(it: String) {
        Log.e(TAG, it)
    }
}