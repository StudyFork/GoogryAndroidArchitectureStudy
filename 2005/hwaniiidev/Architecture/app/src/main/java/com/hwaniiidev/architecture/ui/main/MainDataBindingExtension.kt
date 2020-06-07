package com.hwaniiidev.architecture.ui.main

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hwaniiidev.architecture.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .error(R.drawable.gm_noimage)
        .into(view)
}

@BindingAdapter("htmlTitle")
fun loadHtml(view: TextView, title: String) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        view.text = "제목 : ${Html.fromHtml(title,Html.FROM_HTML_MODE_LEGACY)}"
    }else{
        view.text ="제목 : ${ Html.fromHtml(title)}"
    }
}

@BindingAdapter("loadSubTitle")
fun loadSubTtitle(view : TextView, subtitle: String){
    view.text = "서브제목 : ${subtitle}"
}

@BindingAdapter("loadPubDate")
fun loadPubDate(view : TextView, pubDate: String){
    view.text = "개봉년도 : ${pubDate}"
}

@BindingAdapter("loadDirector")
fun loadDirector(view : TextView, director: String){
    view.text = "감독 : ${director}"
}

@BindingAdapter("loadActor")
fun loadActor(view : TextView, actor: String){
    view.text = "개봉년도 : ${actor}"
}

@BindingAdapter("loadUserRating")
fun loadUserRating(view : TextView, userRating: String){
    view.text = "평점 : ${userRating}"
}