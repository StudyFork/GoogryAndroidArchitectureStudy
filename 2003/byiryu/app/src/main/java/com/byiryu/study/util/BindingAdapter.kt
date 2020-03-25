package com.byiryu.study.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["image_url", "image_scale_type"], requireAll = false)
    fun setImage(view: ImageView, url: String?, imageScaleType: String?) {
        Glide.with(view)
            .run {
                this.clear(view)
                this.load(url)
                    .apply(
                        RequestOptions()
                        .apply {
                            when (imageScaleType) {
                                ImageView.ScaleType.CENTER_CROP.name.toLowerCase() -> optionalCenterCrop()
                                ImageView.ScaleType.CENTER_INSIDE.name.toLowerCase() -> optionalCenterInside()
                                ImageView.ScaleType.FIT_CENTER.name.toLowerCase() -> optionalFitCenter()
                            }
                        }
                    )
                    .into(view)
            }
    }
}