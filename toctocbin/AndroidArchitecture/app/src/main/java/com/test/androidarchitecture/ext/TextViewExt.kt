package com.test.androidarchitecture.ext

import android.widget.TextView
import androidx.core.content.ContextCompat


fun TextView.setTextColorRes(resId: Int) {
    setTextColor(ContextCompat.getColor(context, resId))
}



