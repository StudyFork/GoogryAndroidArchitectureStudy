package io.github.sooakim.util.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(
    value = [
        "imgSrc",
        "imgPlaceholder",
        "imgError"
    ], requireAll = false
)
fun ImageView.bindImageSrc(src: String?, placeholder: Drawable?, error: Drawable?) {
    if (src == null) return
    Glide.with(this)
        .load(src)
        .error(error)
        .placeholder(placeholder)
        .into(this)
}