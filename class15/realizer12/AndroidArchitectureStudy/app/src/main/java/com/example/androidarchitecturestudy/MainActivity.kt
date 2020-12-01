package com.example.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecturestudy.adapter.MovieListRecyclerViewAdapter
import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.androidarchitecturestudy.databinding.ActivityMainBinding
import com.example.androidarchitecturestudy.presenter.MovieContract
import com.example.androidarchitecturestudy.presenter.MoviePresenter
import com.example.androidarchitecturestudy.room.SearchedDataBase
import com.example.androidarchitecturestudy.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {


    // 리사이클러뷰 어뎁터
    private lateinit var recyclerViewAdapter: MovieListRecyclerViewAdapter

    private val moviePresenter = MoviePresenter(this, MovieRepositoryImpl())

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setRecyclerView()
    }


    private fun setupDataBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.thisActivity = this
    }

    override fun hideKeyBoard() {
        hideKeyboard()
    }

    override fun showMovieResultEmpty() {
        updateRecyclerView(emptyList())
        Toast.makeText(this, R.string.main_no_search_result, Toast.LENGTH_SHORT).show();
    }


    override fun showSearchQueryEmpty() {
        Toast.makeText(this, R.string.main_no_search_query, Toast.LENGTH_SHORT).show();
    }

    // 리사이클러뷰 세팅
    private fun setRecyclerView() {
        recyclerViewAdapter = MovieListRecyclerViewAdapter()
        recycler_view_main_movie_list.apply {
            adapter = recyclerViewAdapter
        }
    }

    //에러 로그
    override fun showError(throwable: Throwable) {
        Log.e("check_log", "Failed", throwable)
    }


    // 리사이클러뷰 데이터 업데이트
    override fun updateRecyclerView(movieList: List<GetMovieInfo.MovieData>) {
        recyclerViewAdapter.setMovieData(movieList)
    }


    //영화 검색
    fun searchMovie(view: View) {
        val database = SearchedDataBase.getInstance(applicationContext)
        moviePresenter.getMovieData(edit_main_search_movie.text.toString())
        moviePresenter.saveSearchQuery(edit_main_search_movie.text.toString(), database!!)
    }


    fun showRecentSearchList(view: View) {
        startActivityForResult(
            Intent(this, RecentSearchActivity::class.java),
            RECENT_SEARCH_REQUEST_CODE
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RECENT_SEARCH_REQUEST_CODE) {
            data?.getStringExtra(SELECTED_MOVIE_QUERY_DATA)?.let {
                moviePresenter.getMovieData(it)
                edit_main_search_movie.apply {
                    requestFocus()
                    setText(it)
                    setSelection(length())
                }
            }
        }
    }


}