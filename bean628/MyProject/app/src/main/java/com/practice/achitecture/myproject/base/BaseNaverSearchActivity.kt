package com.practice.achitecture.myproject.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.main.SearchBlogAndNewsAdapter
import com.practice.achitecture.myproject.main.SearchMovieAndBookAdapter
import com.practice.achitecture.myproject.main.SearchedItemClickListener
import com.practice.achitecture.myproject.model.SearchedItem

abstract class BaseNaverSearchActivity<P : BaseContract.Presenter, DB : ViewDataBinding>(@LayoutRes contentLayoutId: Int) :
    BaseActivity<P, DB>(contentLayoutId) {

    open var searchType: SearchType = SearchType.MOVIE
    open var searchMovieAndBookAdapter: SearchMovieAndBookAdapter? = null
    open var searchBlogAndNewsAdapter: SearchBlogAndNewsAdapter? = null
    private val searchedItemListener: SearchedItemClickListener =
        object : SearchedItemClickListener {
            override fun onItemClick(item: SearchedItem) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        searchMovieAndBookAdapter = SearchMovieAndBookAdapter(searchedItemListener)
        searchBlogAndNewsAdapter = SearchBlogAndNewsAdapter(searchedItemListener)
    }


}