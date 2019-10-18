package com.jskim5923.architecturestudy.main

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter

object MainBindingAdapter {
    @BindingAdapter("updateItem")
    @JvmStatic
    fun ViewPager.updateItem(itemList: List<String>?) {
        itemList?.let {
            (adapter as? ViewPagerAdapter)?.updateItem(itemList)
        }
    }
}