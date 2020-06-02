package com.lllccww.studyforkproject.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl
import com.lllccww.studyforkproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {
    private var start = 1
    private var total = 0
    private var display = 0
    private lateinit var movieListAdapter: MovieListAdapter

    private lateinit var binding: ActivityMainBinding


    private val mainPresenter by lazy {
        val naverMoviesRepositoryImpl = NaverMovieRepositoryImpl(MovieRemoteDataSourceImpl())
        MainPresenter(naverMoviesRepositoryImpl, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this@MainActivity

        movieListAdapter = MovieListAdapter { movieItem ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieItem.link))
            startActivity(intent)
        }

        binding.rvMovieList.adapter = movieListAdapter



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

    }


    override fun showMovieList(items: List<MovieItem>) {
        movieListAdapter.addItems(items)
    }

    override fun showFailGetData(msg: String) {
        toastMsg(msg, 0)
    }

    override fun showMovieNoResult() {
        toastMsg("검색 결과가 없습니다.", 0)

    }

    override fun showMovieEmptySearchQuery() {
        toastMsg("검색어를 입력해주세요.", 0)

    }

    override fun hideKeyboard() {
        val view = this.currentFocus

        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun toastMsg(msg: String, toastLength: Int) {
        Toast.makeText(applicationContext, msg, toastLength).show()
    }

    fun getSearchMovie(keyWord: String) {
        mainPresenter.getSearchMovie(keyWord)
    }


}
