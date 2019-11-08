package com.android.studyfork.ui.main.adapter

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

/**
 * created by onemask
 */
object MainBindingAdapter {
    @BindingAdapter("updateItem")
    @JvmStatic
    fun ViewPager.updateItem(pairItem: Pair<List<String>, List<String>>?) {
        pairItem?.let {
            (adapter as? ViewPagerAdapter)?.setData(pairItem)
        }
    }
}