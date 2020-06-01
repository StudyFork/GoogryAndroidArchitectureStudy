package r.test.rapp.networks

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import r.test.rapp.R


class ImageLoader {
    companion object {
        @BindingAdapter("thumbnail")
        @JvmStatic
        fun load(iv: ImageView, url: String?) {
            Glide.with(iv)
                .load(url)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(iv)
        }
    }
}