package mi.song.class12android.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bind:imgUrl")
fun loadImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView)
            .load(imgUrl)
            .apply(RequestOptions().override(80, 80))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imgView)
    }
}