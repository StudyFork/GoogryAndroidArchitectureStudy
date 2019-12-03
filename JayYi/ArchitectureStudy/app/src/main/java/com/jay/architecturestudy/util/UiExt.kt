package com.jay.architecturestudy.util

import android.content.res.Resources

fun Int.toPx(): Int = this * Resources.getSystem().displayMetrics.density.toInt()

fun Int.toDp(): Int = this / Resources.getSystem().displayMetrics.density.toInt()