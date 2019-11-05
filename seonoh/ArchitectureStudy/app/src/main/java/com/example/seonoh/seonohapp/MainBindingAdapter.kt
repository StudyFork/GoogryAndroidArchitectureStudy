package com.example.seonoh.seonohapp

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

@BindingAdapter("setMarketNameData")
fun ViewPager.setMarketNameData(data: List<String>?) {
    data?.let {
        (adapter as? TabPagerAdapter)?.setData(data)
    }
}

@BindingAdapter("addTab")
fun TabLayout.addTab(data: List<String>?) {
    data?.let {
        data.forEach {
            addTab(newTab().setText("${it.substringBefore("-")}"))
        }
    }
}