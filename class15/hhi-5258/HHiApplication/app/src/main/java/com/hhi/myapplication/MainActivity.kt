package com.hhi.myapplication

import android.content.Context
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {
    private val recyclerAdapter = RecyclerAdapter()
    private val mainPresenter = MainPresenter(this, NaverRepositoryDataSourceImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
        setUpListener()
    }

    private fun setUpUI() {
        main_recyclerview.setHasFixedSize(false)
        main_recyclerview.adapter = recyclerAdapter
    }

    private fun setUpListener() {
        main_btn_search.setOnClickListener {
            val searchText = main_edit_search.text.toString()
            mainPresenter.searchMovie(searchText)
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
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}