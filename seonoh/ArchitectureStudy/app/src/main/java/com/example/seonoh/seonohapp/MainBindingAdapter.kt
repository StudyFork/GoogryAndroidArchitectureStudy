package com.example.seonoh.seonohapp

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

object MainBindingAdapter {
    @BindingAdapter("setMarketNameData")
    @JvmStatic
    fun ViewPager.setMarketNameData(data : List<String>?){
        data?.let {
            (adapter as? TabPagerAdapter)?.setData(data)
        }
    }
}