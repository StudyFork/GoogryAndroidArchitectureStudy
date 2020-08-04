package com.example.study

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.data.model.NaverApiData

@BindingAdapter("loadImgUrl")
fun loadImg(imgView: ImageView, imgUrl: String){
    Glide.with(imgView)
        .load(imgUrl)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(imgView)
}

@BindingAdapter("fromHtml")
fun fromHtml(textView: TextView, string: String) {
   val text =  HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_MODE_LEGACY)
    textView.text = text
}

@BindingAdapter("movieLists")
fun movieLists(recyclerView: RecyclerView, data: List<NaverApiData.Item>?){
    data?.let {
        val viewAdapter = recyclerView.adapter as? RecyclerAdapter
        viewAdapter?.setItem(data)
    }
}

