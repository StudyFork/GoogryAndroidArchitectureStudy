package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.data.repository.MovieRepositoryDataSet
import kotlinx.android.synthetic.main.activity_search_movie.*

class SearchMovieActivity : AppCompatActivity(), SearchMovieContract.View {
    private val TAG = "SearchMovieActivity"

    private lateinit var movieRepositoryDataSet: MovieRepositoryDataSet
    private lateinit var presenter: SearchMoviePresenter

    private val movieAdapter = SearchMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        initView()
        setOnclickListener()
        presenter = SearchMoviePresenter(this, movieRepositoryDataSet)

    }

    private fun initView() {
        movieRepositoryDataSet = application as MovieRepositoryDataSet
        rv_movie.setHasFixedSize(true)
        rv_movie.adapter = movieAdapter
    }

    private fun setOnclickListener() {
        // 영화 검색 로직
        btn_search.setOnClickListener(View.OnClickListener {

            var etMovieTitle = et_movie_title.text.toString()
            presenter.searchMovie(etMovieTitle)
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