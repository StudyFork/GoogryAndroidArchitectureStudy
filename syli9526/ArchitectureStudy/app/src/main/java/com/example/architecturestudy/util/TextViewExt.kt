package com.example.architecturestudy.util

import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setColor(color: Int){
    setTextColor(ContextCompat.getColor(context, color))
}