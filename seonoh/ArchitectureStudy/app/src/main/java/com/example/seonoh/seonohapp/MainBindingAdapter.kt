package com.example.seonoh.seonohapp

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

@BindingAdapter("setMarketNameData")
fun ViewPager.setMarketNameData(data: List<String>?) {
    data?.let {
        (adapter as? TabPagerAdapter)?.setData(data)
    }
}