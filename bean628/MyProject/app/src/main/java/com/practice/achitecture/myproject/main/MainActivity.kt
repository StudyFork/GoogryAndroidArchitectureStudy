package com.practice.achitecture.myproject.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseActivity
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.makeToast
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.network.retrofitErrorHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.Presenter>(R.layout.activity_main),
    View.OnClickListener,
    MainContract.View {

    override val presenter: MainContract.Presenter = MainPresenter(this)

    private var searchType: SearchType = SearchType.MOVIE
    private var searchMovieAndBookAdapter: SearchMovieAndBookAdapter? = null
    private var searchBlogAndNewsAdapter: SearchBlogAndNewsAdapter? = null
    private val searchedItemListener: SearchedItemClickListener =
        object : SearchedItemClickListener {
            override fun onItemClick(item: SearchedItem) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerOnClickListener()
        initAdapter()
    }

    private fun initAdapter() {
        searchMovieAndBookAdapter = SearchMovieAndBookAdapter(searchedItemListener)
        searchBlogAndNewsAdapter = SearchBlogAndNewsAdapter(searchedItemListener)
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
                search(SearchType.MOVIE)
            R.id.btn_search_type_book ->
                search(SearchType.BOOK)
            R.id.btn_search_type_blog ->
                search(SearchType.BLOG)
            R.id.btn_search_type_news ->
                search(SearchType.NEWS)
        }

    }

    private fun search(searchType: SearchType) {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input_search_sth.windowToken, 0)

        val word = input_search_sth.text.toString()
        this.searchType = searchType
        presenter.searchIfNotEmpty(word, searchType)
    }

    override fun isEmpty() {
        makeToast(R.string.toast_empty_word)
    }

    override fun showSearchResultBlogOrNews(items: List<SearchedItem>) {
        searchBlogAndNewsAdapter?.notifyDataSetChanged(items)
        rv_searched_list.adapter = searchBlogAndNewsAdapter
    }

    override fun showSearchResultMovieOrBook(items: List<SearchedItem>) {
        searchMovieAndBookAdapter?.notifyDataSetChanged(items)
        rv_searched_list.adapter = searchMovieAndBookAdapter
    }

    override fun searchingOnFailure(throwable: Throwable) {
        retrofitErrorHandler(this@MainActivity, throwable)
    }


}
