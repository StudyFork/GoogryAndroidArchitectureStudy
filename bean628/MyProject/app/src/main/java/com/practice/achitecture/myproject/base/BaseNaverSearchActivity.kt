package com.practice.achitecture.myproject.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.main.SearchMovieAndBookAdapter

abstract class BaseNaverSearchActivity<DB : ViewDataBinding>(@LayoutRes contentLayoutId: Int) :
    BaseActivity<DB>(contentLayoutId) {

    open var searchType: SearchType = SearchType.MOVIE
    open var searchMovieAndBookAdapter: SearchMovieAndBookAdapter? = null
//    open var searchBlogAndNewsAdapter: SearchBlogAndNewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        searchMovieAndBookAdapter = SearchMovieAndBookAdapter()
//        searchBlogAndNewsAdapter = SearchBlogAndNewsAdapter()
    }


}