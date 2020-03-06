package app.ch.study.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String) {
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .into(this)
}