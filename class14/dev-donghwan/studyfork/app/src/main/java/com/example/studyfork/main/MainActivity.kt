package com.example.studyfork.main

import android.content.Intent
import android.os.Bundle
import com.example.studyfork.MovieRecyclerAdapter
import com.example.studyfork.R
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.data.local.LocalDataSourceImpl
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.remote.RemoteDataSourceImpl
import com.example.studyfork.data.repository.RepositoryImpl
import com.example.studyfork.databinding.ActivityMainBinding
import com.example.studyfork.recent.RecentSearchListActivity

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    MainContract.View {

    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            RepositoryImpl(
                RemoteDataSourceImpl(),
                LocalDataSourceImpl(getSharedPreferences("local", MODE_PRIVATE))
            )
        )
    }

    private val requestCode = 100

    private val recyclerAdapter = MovieRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        binding.recMovie.adapter = recyclerAdapter
    }

    override fun searchMovie() {
        binding.edtQuery.text.toString().run {
            presenter.requestMovieList(this)
        }
    }

    override fun onDestroy() {
        presenter.requestClearDisposable()
        super.onDestroy()
    }

    override fun showQueryEmpty() {
        showToast("검색어를 입력해주세요")
    }

    override fun showMovieEmpty() {
        showToast("검색 결과가 없습니다.")
    }

    override fun showMovieError() {
        showToast("데이터를 불러오는 중에 문가 발생했습니다.")
    }

    override fun showRecentSearchListActivity() {
        Intent(this, RecentSearchListActivity::class.java).apply {
            startActivityForResult(this, requestCode)
        }
    }

    override fun showMovieList(list: List<MovieSearchResponse.MovieItem>) {
        recyclerAdapter.itemChange(list)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            if (resultCode != RESULT_OK) return
            data?.run {
                presenter.requestMovieList(this.getStringExtra(SEARCH_ITEM) ?: "")
            }
        }
    }

    companion object {
        const val SEARCH_ITEM = "SEARCH_ITEM"
    }
}