package com.example.androidarchitecturestudy

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecturestudy.adapter.MovieListRecyclerViewAdapter
import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 리사이클러뷰 어뎁터
    private lateinit var recyclerViewAdapter: MovieListRecyclerViewAdapter


    private val movieRepositoryImpl = MovieRepositoryImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        initEventListeners()
    }

    // 리사이클러뷰 세팅
    private fun setRecyclerView() {
        recyclerViewAdapter = MovieListRecyclerViewAdapter()
        recycler_view_main_movie_list.apply {
            adapter = recyclerViewAdapter
        }
    }

    // 리사이클러뷰 데이터 업데이트
    private fun updateRecyclerView(movieList: List<GetMovieInfo.MovieData>) {
        recyclerViewAdapter.setMovieData(movieList)
    }


    // 영화 검색 실행
    private fun initEventListeners() {
        // 검색 버튼 눌릴때
        btn_main_search_movie.setOnClickListener {
            getMovieData(edit_main_search_movie.text.toString())
        }

        // edittext search action 눌릴때
        edit_main_search_movie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getMovieData(edit_main_search_movie.text.toString())
                true
            }else{
                false
            }
        }

    }





    // 서버에  영화 데이터 받아옴
    // TODO: 2020/11/01 추후  코루틴 또는 Rx로 변경하기
    private fun getMovieData(searchQuery: String) {
        //영화 검색 실행
        movieRepositoryImpl.getMovieSearchResult(searchQuery,{
            it.movieList?.let { it->
                updateRecyclerView(it)
            }

        },{
            Log.v("check_log", it.message.toString())
        })
    }

}