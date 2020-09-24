package com.camai.archtecherstudy.extension

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("ImageDrawable", "ImageDrawableError")
fun ImageViewAdapter(view: ImageView, res: String?, error: Drawable) {
    val options = RequestOptions()
        .error(error)

    Glide.with(view.context)
        .load(res)
        .apply(options)
        .into(view)

}


@BindingAdapter("ViewVisible")
fun visibleProgress(view: View, bool: Boolean){
    view.visibility = if (bool) View.VISIBLE else View.GONE

}
