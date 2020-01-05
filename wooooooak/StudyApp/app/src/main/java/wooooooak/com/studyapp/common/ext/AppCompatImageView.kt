package wooooooak.com.studyapp.common.ext

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("imageUrl")
fun AppCompatImageView.renderImage(url: String) {
    load(url)
}