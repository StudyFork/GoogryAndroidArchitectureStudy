package com.ironelder.androidarchitecture.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.component.CustomListViewAdapter
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.ui.CustomPagerAdapter
import com.ironelder.androidarchitecture.view.mainview.MainActivity

@BindingAdapter("setImageViewVisible")
fun setImageViewVisible(view: ImageView, url: String?) {
    if (url == null) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background).into(view)
}

@BindingAdapter("setListItems")
fun setListItems(view: RecyclerView, items: ObservableArrayList<ResultItem>?) {
    val adapter: CustomListViewAdapter = view.adapter as CustomListViewAdapter
    adapter.setItemList(items)
}

@BindingAdapter("setFragmentPageAdapter")
fun setFragmentPageAdapter(viewpager: ViewPager, activity: MainActivity) {
    viewpager.adapter = CustomPagerAdapter(activity, activity.supportFragmentManager)
}

@BindingAdapter("setViewPager")
fun setViewPager(layout:TabLayout, viewpager: ViewPager){
    layout.setupWithViewPager(viewpager)
}