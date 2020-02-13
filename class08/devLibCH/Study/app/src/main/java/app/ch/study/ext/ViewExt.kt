package app.ch.study.ext

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["visible"])
fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}