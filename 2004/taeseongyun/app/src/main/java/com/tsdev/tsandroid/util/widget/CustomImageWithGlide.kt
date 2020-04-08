package com.tsdev.tsandroid.util.widget

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tsdev.tsandroid.R

class CustomImageWithGlide @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defAttrs: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defAttrs) {
    fun loadMovieImage(url: String) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
    }
}