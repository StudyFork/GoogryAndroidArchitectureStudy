package com.mtjin.androidarchitecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.utils.MyApplication
import retrofit2.HttpException


class MovieSearchActivity : AppCompatActivity() {

    private lateinit var etInput: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var pbLoading: ProgressBar
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var query: String
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        initView()
        initAdapter()
        initListener()
    }

    private fun initView() {
        myApplication = application as MyApplication
        etInput = findViewById(R.id.et_input)
        btnSearch = findViewById(R.id.btn_search)
        rvMovies = findViewById(R.id.rv_movies)
        pbLoading = findViewById(R.id.pb_loading)
    }

    private fun initAdapter() {
        movieAdapter =
            MovieAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        rvMovies.layoutManager = linearLayoutManager
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                requestPagingMovie(query, totalItemsCount + 1)
            }
        }
        rvMovies.addOnScrollListener(scrollListener)
        rvMovies.adapter = movieAdapter
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
            query = etInput.text.toString().trim()
            if (query.isEmpty()) {
                onToastMessage("검색어를 입력해주세요.")
            } else {
                onToastMessage("잠시만 기다려주세요.")
                requestMovie(query)
            }
        }
    }

    private fun requestMovie(query: String) {
        showLoading()
        scrollListener.resetState()
        myApplication.movieRepository.getSearchMovies(query,
            success = {
                if (it.isEmpty()) {
                    onToastMessage("해당 영화는 존재하지 않습니다.")
                } else {
                    movieAdapter.clear()
                    movieAdapter.setItems(it)
                    onToastMessage("영화를 불러왔습니다.")
                }
                hideLoading()
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> onToastMessage("네트워크에 문제가 있습니다.")
                    else -> onToastMessage(it.message.toString())
                }
                hideLoading()
            })
    }

    fun requestPagingMovie(query: String, offset: Int) {
        showLoading()
        myApplication.movieRepository.getPagingMovies(query, offset,
            success = {
                if (it.isEmpty()) {
                    onToastMessage("마지막 페이지")
                } else {
                    movieAdapter.setItems(it)
                    onToastMessage("영화를 불러왔습니다.")
                }
                hideLoading()
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> onToastMessage("네트워크에 문제가 있습니다.")
                    else -> onToastMessage(it.message.toString())
                }
                hideLoading()
            })
    }

    private fun onToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pbLoading.visibility = View.GONE
    }

    companion object {
        const val TAG = "MovieSearchActivity"
    }

}
