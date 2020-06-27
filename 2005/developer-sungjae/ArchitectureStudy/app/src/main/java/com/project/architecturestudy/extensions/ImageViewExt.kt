package com.project.architecturestudy.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.project.architecturestudy.components.Constants
import com.squareup.picasso.Picasso

@BindingAdapter("setImgUrl", "setAlternativeImg", requireAll = true)
fun ImageView.setImgUrl(imgUrl: String?, alternativeImg: Drawable) {
    imgUrl?.let { nImgUrl ->
        Picasso.get()
            .load(nImgUrl.ifEmpty {
                Constants.NO_IMAGE_URL
            })
            .placeholder(alternativeImg)
            .centerCrop()
            .error(alternativeImg)
            .fit()
            .into(this)
    }
}