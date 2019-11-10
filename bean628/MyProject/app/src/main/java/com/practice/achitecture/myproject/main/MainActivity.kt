package com.practice.achitecture.myproject.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.practice.achitecture.myproject.BaseActivity
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.data.source.remote.NaverRepository
import com.practice.achitecture.myproject.makeToast
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.network.retrofitErrorHandler
import common.SEARCH_TYPE_BLOG
import common.SEARCH_TYPE_BOOK
import common.SEARCH_TYPE_MOVIE
import common.SEARCH_TYPE_NEWS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    View.OnClickListener,
    MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    private var searchType: Int = SEARCH_TYPE_MOVIE
    private var searchMovieAndBookAdapter: SearchMovieAndBookAdapter? = null
    private var searchBlogAndNewsAdapter: SearchBlogAndNewsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerOnClickListener()
        initAdapter()

        presenter = MainPresenter(this, NaverRepository)
    }

    private fun initAdapter() {
        searchMovieAndBookAdapter =
            SearchMovieAndBookAdapter()
        searchBlogAndNewsAdapter =
            SearchBlogAndNewsAdapter()
    }

    private fun registerOnClickListener() {
        btn_search.setOnClickListener(this)
        btn_search_type_movie.setOnClickListener(this)
        btn_search_type_book.setOnClickListener(this)
        btn_search_type_blog.setOnClickListener(this)
        btn_search_type_news.setOnClickListener(this)
        input_search_sth.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    search(searchType)
                    true
                }
                else -> false
            }
        }
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_search -> search(searchType)
            R.id.btn_search_type_movie ->
                search(SEARCH_TYPE_MOVIE)
            R.id.btn_search_type_book ->
                search(SEARCH_TYPE_BOOK)
            R.id.btn_search_type_blog ->
                search(SEARCH_TYPE_BLOG)
            R.id.btn_search_type_news ->
                search(SEARCH_TYPE_NEWS)
        }

    }

    private fun search(searchType: Int) {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input_search_sth.windowToken, 0)

        val word = input_search_sth.text.toString()
        if (word.isEmpty()) {
            makeToast(R.string.toast_empty_word)
            return
        }
        this.searchType = searchType
        val category = when (searchType) {
            SEARCH_TYPE_MOVIE -> "movie"
            SEARCH_TYPE_BOOK -> "book"
            SEARCH_TYPE_BLOG -> "blog"
            SEARCH_TYPE_NEWS -> "news"
            else -> "movie"
        }

        presenter.searchWordByNaver(category, word)
    }


    override fun searchingOnSuccess(items: List<SearchedItem>) {
        when (this@MainActivity.searchType) {
            SEARCH_TYPE_MOVIE, SEARCH_TYPE_BOOK -> {
                searchMovieAndBookAdapter?.notifyDataSetChanged(items)
                rv_searched_list.adapter = searchMovieAndBookAdapter
            }
            SEARCH_TYPE_BLOG, SEARCH_TYPE_NEWS -> {
                searchBlogAndNewsAdapter?.notifyDataSetChanged(items)
                rv_searched_list.adapter = searchBlogAndNewsAdapter
            }
        }
    }

    override fun searchingOnFailure(throwable: Throwable) {
        retrofitErrorHandler(this@MainActivity, throwable)
    }

}
