package com.example.androidarchitecturestudy

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecturestudy.adapter.MovieListRecyclerViewAdapter
import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.androidarchitecturestudy.presenter.MovieContract
import com.example.androidarchitecturestudy.presenter.MoviePresenter
import com.example.androidarchitecturestudy.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {

    // 리사이클러뷰 어뎁터
    private lateinit var recyclerViewAdapter: MovieListRecyclerViewAdapter

    private val movieRepositoryImpl = MovieRepositoryImpl()
    private val moviePresenter = MoviePresenter(this, movieRepositoryImpl)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()
        initEventListeners()
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
        throwable.message?.let { Log.e("check_log", it) }
    }


    // 리사이클러뷰 데이터 업데이트
    override fun updateRecyclerView(movieList: List<GetMovieInfo.MovieData>) {
        recyclerViewAdapter.setMovieData(movieList)
    }

    // 영화 검색 실행
    private fun initEventListeners() {
        // 검색 버튼 눌릴때
        btn_main_search_movie.setOnClickListener {
            moviePresenter.getMovieData(edit_main_search_movie.text.toString())
        }

        // edittext search action 눌릴때
        edit_main_search_movie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                moviePresenter.getMovieData(edit_main_search_movie.text.toString())
                true
            } else {
                false
            }
        }
    }

}