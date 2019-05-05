package dev.daeyeon.gaasproject.ext

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.widget.TextView

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