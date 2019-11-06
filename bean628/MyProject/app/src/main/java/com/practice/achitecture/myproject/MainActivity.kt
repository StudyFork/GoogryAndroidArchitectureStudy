package com.practice.achitecture.myproject

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSource
import com.practice.achitecture.myproject.model.SearchedItem
import common.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private var searchType: Int = SEARCH_TYPE_MOVIE
    private var searchMovieAndBookAdapter: SearchMovieAndBookAdapter? = null
    private var searchBlogAndNewsAdapter: SearchBlogAndNewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerOnClickListener()
        initAdapter()
    }

    private fun initAdapter() {
        searchMovieAndBookAdapter = SearchMovieAndBookAdapter()
        searchBlogAndNewsAdapter = SearchBlogAndNewsAdapter()
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
            Toast.makeText(
                this@MainActivity,
                getString(R.string.toast_empty_word),
                Toast.LENGTH_SHORT
            ).show()
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

        NaverRepository.searchWordByNaver(
            category,
            word,
            object : NaverRemoteDataSource.GetResultOfSearchingCallBack {
                override fun onSuccessButEmptyData() {
                    this@MainActivity.makeToast(R.string.toast_empty_result)
                }

                override fun onSuccess(items: List<SearchedItem>) {
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

                override fun onFailure(errorMsg: String) {
                    this@MainActivity.makeToast(errorMsg)
                }
            })
    }

}
