package com.onit.googlearchitecturestudy

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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

    private fun String.removeTag(): String {
        return Html.fromHtml(this).toString()
    }
}