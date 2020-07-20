package mi.song.class12android.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageBindingUtil {

    @JvmStatic
    @BindingAdapter("bind:imgUrl")
    fun loadImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            Glide.with(imgView)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imgView)
        }
    }
}