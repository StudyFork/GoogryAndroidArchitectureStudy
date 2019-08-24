package com.jake.archstudy.ext

import android.annotation.SuppressLint
import android.widget.TextView

@SuppressLint("SetTextI18n")
fun TextView.setChangeColor(change: String) {
    val color = context.getColor(
        when (change) {
            "RISE" -> android.R.color.holo_red_light
            "EVEN" -> android.R.color.darker_gray
            "FALL" -> android.R.color.holo_blue_light
            else -> android.R.color.darker_gray
        }
    )
    setTextColor(color)
}
