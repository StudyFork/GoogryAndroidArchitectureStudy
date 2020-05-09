package io.github.jesterz91.study.presentation.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .into(this)
    }
}