package dev.daeyeon.gaasproject.ext

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("rateColor")
fun TextView.setRateColor(rate: String) {
    this.setTextColor(
        if (rate.startsWith("-")) {
            ContextCompat.getColor(this.context, android.R.color.holo_red_light)
        } else {
            ContextCompat.getColor(this.context, android.R.color.holo_blue_bright)
        }
    )
}