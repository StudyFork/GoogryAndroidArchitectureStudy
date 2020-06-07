package r.test.rapp.networks

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import r.test.rapp.R


class ImageViewExtension {
    companion object {
        @BindingAdapter("load")
        @JvmStatic
        fun ImageView.load(url: String) {
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(this)
        }
    }
}