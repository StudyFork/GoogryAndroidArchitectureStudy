package com.practice.achitecture.myproject.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchActivity
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.local.NaverDatabase
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.databinding.ActivityMainBinding
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.history.HistoryActivity
import com.practice.achitecture.myproject.makeToast
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.network.RetrofitClient
import com.practice.achitecture.myproject.network.errorHandler
import com.practice.achitecture.myproject.openActivity
import com.practice.achitecture.myproject.util.AppExecutors
import common.NAVER_API_BASE_URL

class MainActivity :
    BaseNaverSearchActivity<MainContract.Presenter, ActivityMainBinding>(R.layout.activity_main),
    View.OnClickListener,
    MainContract.View {

    override val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            NaverRepository.getInstance(
                NaverRemoteDataSourceImpl(RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()),
                NaverLocalDataSourceImpl.getInstance(
                    AppExecutors(),
                    NaverDatabase.getInstance(applicationContext).naverDao(),
                    this.cacheDir.absolutePath
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerOnClickListener()
        presenter.loadCache()
    }

    private fun registerOnClickListener() {
        binding.btnSearch.setOnClickListener(this)
        binding.btnHistory.setOnClickListener(this)
        binding.btnSearchTypeMovie.setOnClickListener(this)
        binding.btnSearchTypeBook.setOnClickListener(this)
        binding.btnSearchTypeBlog.setOnClickListener(this)
        binding.btnSearchTypeNews.setOnClickListener(this)
        binding.inputSearchSth.setOnEditorActionListener { _, actionId, _ ->
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
            R.id.btn_history -> this.openActivity(HistoryActivity::class.java)
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
        imm.hideSoftInputFromWindow(binding.inputSearchSth.windowToken, 0)

        val word = binding.inputSearchSth.text.toString()
        this.searchType = searchType
        presenter.searchIfNotEmpty(word, searchType)
    }

    override fun searchWordEmpty() {
        makeToast(R.string.toast_empty_word)
    }

    override fun showSearchResultBlogOrNews(items: List<SearchedItem>) {
        searchBlogAndNewsAdapter?.notifyDataSetChanged(items)
        binding.rvSearchedList.adapter = searchBlogAndNewsAdapter
    }

    override fun showSearchResultMovieOrBook(items: List<SearchedItem>) {
        searchMovieAndBookAdapter?.notifyDataSetChanged(items)
        binding.rvSearchedList.adapter = searchMovieAndBookAdapter
    }

    override fun searchingOnFailure(throwable: Throwable) {
        errorHandler(this@MainActivity, throwable)
    }


}
