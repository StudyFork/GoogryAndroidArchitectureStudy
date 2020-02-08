package com.onit.googlearchitecturestudy

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onit.googlearchitecturestudy.ui.main.ResultMovieListRecyclerAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("setListener")
    fun setListener(view: View, listener: () -> Unit) {
        view.setOnClickListener { listener() }
    }

    @JvmStatic
    @BindingAdapter("setImageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setRating")
    fun setRating(ratingBar: RatingBar, rating: Float) {
        ratingBar.rating = rating / 2f
    }

    @JvmStatic
    @BindingAdapter("setTagRemovedText")
    fun setTagRemovedText(textView: TextView, text: String) {
        textView.text = text.removeTag()
    }

    @JvmStatic
    @BindingAdapter("setItem")
    fun RecyclerView.setItem(item: List<Movie>?) {
        if (adapter is ResultMovieListRecyclerAdapter && item != null) {
            (adapter as ResultMovieListRecyclerAdapter).setMovieList(item)
        }
    }

    private fun String.removeTag(): String {
        return Html.fromHtml(this).toString()
    }
}