package common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.practice.achitecture.myproject.R

object GlideWrapper {

    fun showImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.drawable_gray)
            .error(R.drawable.ic_empty_image)
            .into(imageView)
    }
}
