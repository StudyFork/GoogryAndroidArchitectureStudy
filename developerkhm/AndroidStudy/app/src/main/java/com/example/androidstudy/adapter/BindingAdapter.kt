package com.example.androidstudy.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudy.model.data.Item
import com.ironelder.androidarchitecture.view.AdapterSearch

@BindingAdapter("setImageId")
fun setImageResource(v: ImageView, resId: String?) {
    resId.let {
        Glide
            .with(v.context)
            .load(it)
            .centerCrop()
            .into(v)
    }
}

@BindingAdapter("replaceAll")
fun setItems(r: RecyclerView, items: List<Item>) {
    (r?.adapter as AdapterSearch).setItemList(items)
}
