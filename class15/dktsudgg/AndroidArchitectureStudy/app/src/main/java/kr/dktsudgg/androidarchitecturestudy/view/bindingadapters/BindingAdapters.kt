package kr.dktsudgg.androidarchitecturestudy.view.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.dktsudgg.androidarchitecturestudy.R

@BindingAdapter("bindImageUrlToImageView")
fun bindImageUrlToImageView(view: ImageView, imageUrl: String) {
    Glide
        .with(view.context)
        .load(imageUrl)
        .centerInside()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(view)
}