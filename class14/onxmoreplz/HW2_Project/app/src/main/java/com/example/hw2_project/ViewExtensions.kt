package com.example.hw2_project

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw2_project.recentSearch.RecentSearchRecyclerViewAdapter
import org.jetbrains.annotations.Nullable

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url != null) {
        Glide.with(this.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
    } else {
        Glide.with(this.context)
            .load(R.drawable.ic_launcher_foreground)
            .into(this)
    }

}

@BindingAdapter("directorName")
fun TextView.setDirectorName(@Nullable directorName: String) {
    this.text = directorName?.replace("|", "")
}

@BindingAdapter("setQueryList")
fun RecyclerView.setRecentQueryList(recentQueryList: List<String>) {
    val recentSearchRecyclerViewAdapter = adapter as? RecentSearchRecyclerViewAdapter
    recentSearchRecyclerViewAdapter?.updateMovieList(recentQueryList)
}
