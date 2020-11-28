package com.example.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecturestudy.adapter.MovieListRecyclerViewAdapter
import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.androidarchitecturestudy.databinding.ActivityMainBinding
import com.example.androidarchitecturestudy.presenter.MovieContract
import com.example.androidarchitecturestudy.presenter.MoviePresenter
import com.example.androidarchitecturestudy.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {

    // 리사이클러뷰 어뎁터
    private lateinit var recyclerViewAdapter: MovieListRecyclerViewAdapter

    private val moviePresenter = MoviePresenter(this, MovieRepositoryImpl())

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingSet()
        setRecyclerView()
    }


    private fun dataBindingSet(){
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
    fun searchMovie(view : View){
        moviePresenter.getMovieData(edit_main_search_movie.text.toString())
    }


    fun showRecentSearchList(view: View){
        startActivityForResult(Intent(this,RecentSearchActivity::class.java),RECENT_SEARCH_REQUEST_CODE)
    }

}