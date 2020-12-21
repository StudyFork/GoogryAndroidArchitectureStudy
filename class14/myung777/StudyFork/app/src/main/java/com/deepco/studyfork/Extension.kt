package com.deepco.studyfork

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deepco.data.data.model.Item
import com.deepco.data.data.model.RecentSearchData

@BindingAdapter("loadUrl")
fun ImageView.loadImageView(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.drawable.ic_null_image)
        .into(this)
}

@BindingAdapter("titleFormat")
fun TextView.setTitleString(title: String) {
    this.text = title.replace("<b>", "").replace("</b>", "")
}

@BindingAdapter("directorFormat")
fun TextView.setDirectorString(director: String) {
    this.text = director.let {
        if (it.isEmpty()) {
            ""
        } else {
            it.substring(0, director.length - 1).replace("|", ", ")
        }
    }
}

@BindingAdapter("setMovieList")
fun RecyclerView.setMovieList(list: List<Item>?) {
    list ?: return
    val adapter =
        adapter as? MovieRecyclerAdapter ?: MovieRecyclerAdapter().apply { adapter = this }
    adapter.setItemList(list)
}

@BindingAdapter("setRecentSearchList")
fun RecyclerView.setRecentSearchList(list: List<RecentSearchData>) {
    (adapter as RecentSearchRecyclerAdapter).setItemList(list)
}