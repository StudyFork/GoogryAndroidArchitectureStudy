package com.hhi.myapplication.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl
import com.hhi.myapplication.databinding.ActivityMainBinding
import com.hhi.myapplication.recentSearch.RecentSearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainContract.Presenter, ActivityMainBinding>(R.layout.activity_main),
    MainContract.View {
    private val recyclerAdapter =
        MainRecyclerAdapter()
    private val mainPresenter = MainPresenter(
        this,
        NaverRepositoryDataSourceImpl()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpUi()
        setUpListener()
    }

    private fun setUpUi() {
        main_recyclerview.setHasFixedSize(false)
        main_recyclerview.adapter = recyclerAdapter
    }

    private fun setUpListener() {
        main_btn_search.setOnClickListener {
            val searchText = main_edit_search.text.toString()
            mainPresenter.searchMovie(searchText)
        }
        main_btn_recent_search.setOnClickListener {
            val intent = Intent(this, RecentSearchActivity::class.java)
            startActivityForResult(intent, RC_ACTIVITY_FOR_RESULT)
        }
    }

    override fun showMovies(items: ArrayList<MovieData.MovieItem>) {
        recyclerAdapter.setMovieList(items)
    }

    override fun showEmptyQuery() {
        super.showToast("내용을 입력해 주세요")
    }

    override fun showProgressBar() {
        main_progressbar.isVisible = true
    }

    override fun hideProgressBar() {
        main_progressbar.isVisible = false
    }

    override fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_ACTIVITY_FOR_RESULT -> mainPresenter.searchMovie(data!!.getStringExtra("query"))
            }
        }
    }

    companion object {
        private const val RC_ACTIVITY_FOR_RESULT: Int = 100
    }
}