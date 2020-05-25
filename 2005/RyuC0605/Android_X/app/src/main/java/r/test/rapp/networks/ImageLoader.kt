package r.test.rapp.networks

import android.widget.ImageView
import com.bumptech.glide.Glide
import r.test.rapp.R

class ImageLoader {
    fun load(url: String, iv: ImageView) {
        Glide.with(iv.context)
            .load(url)
            .placeholder(R.drawable.no_image)
            .centerCrop()
            .into(iv)
    }
}