package com.practice.achitecture.myproject.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem
import common.GlideWrapper

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:setImageUrl")
    fun showImage(view: ImageView, url: String) {
        if (url.isNotEmpty()) {
            GlideWrapper.showImage(view, url)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:setItemList", "bind:setSearchType")
    fun setItemList(view: RecyclerView, itemList: List<SearchedItem>, searchType: SearchType) {
        (view.adapter as SearchMovieAndBookAdapter).searchType = searchType
        (view.adapter as SearchMovieAndBookAdapter).notifyDataSetChanged(itemList)
    }
}
