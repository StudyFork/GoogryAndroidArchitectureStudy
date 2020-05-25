package com.lllccww.studyforkproject.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var start = 1
    private var total = 0
    private var display = 0
    private val movieListAdapter =
        MovieListAdapter()
    //private val naverMovieRepositoryImpl = NaverMovieRepositoryImpl()

    private val naverMoviesRepositoryImpl by lazy {
        NaverMovieRepositoryImpl(MovieRemoteDataSourceImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.adapter = movieListAdapter

        movieListAdapter.onClick = { item: MovieItem ->
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }


        rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as? LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = movieListAdapter.itemCount - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    Log.d("fail : ", "스크롤 최하단")
                    if (start < total) {
                        //requestSearchMovie(start + 10)
                    } else {
                        Toast.makeText(this@MainActivity, "마지막 페이지입니다.", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })


        btn_search.setOnClickListener {
            closeKeyboard()
            requestSearchMovie()
        }
    }


    //영화정보 요청
    private fun requestSearchMovie() {
        val keword = edt_search_keyword.text.toString()

        naverMoviesRepositoryImpl.getSearchMovie(
            keword,
            success = { movieItem ->
                movieApiSucess(movieItem)
            },
            failure = { t ->
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }

        )
    }


    //키보드 숨기기
    private fun closeKeyboard() {
        val view = this.currentFocus

        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    //영화검색Api success시 동작함수
    private fun movieApiSucess(movieItem: List<MovieItem>) {
        if (movieItem.isNullOrEmpty()) {
            Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
        } else {
            movieListAdapter.addItems(movieItem)
        }
    }


}
