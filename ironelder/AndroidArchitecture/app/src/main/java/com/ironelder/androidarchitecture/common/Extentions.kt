package com.ironelder.androidarchitecture.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.component.CustomListViewAdapter
import com.ironelder.androidarchitecture.data.ResultItem

@BindingAdapter("setImageViewVisible")
fun setImageViewVisible(view: ImageView, url:String?){
    if(url.isNullOrEmpty()){
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(view: ImageView, url: String?) {
    if(!url.isNullOrEmpty()){
        Glide.with(view.context).load(url).centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background).into(view)
    }
}

@BindingAdapter("setListItems")
fun setListItems(view:RecyclerView, items:ObservableArrayList<ResultItem>?){
    if(!items.isNullOrEmpty()){
        val adapter:CustomListViewAdapter = view.adapter as CustomListViewAdapter
        adapter.setItemList(items)
    }
}