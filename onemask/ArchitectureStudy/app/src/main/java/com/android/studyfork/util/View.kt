package com.android.studyfork.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * created by onemask
 */

fun ViewGroup.inflate(@LayoutRes resource : Int, attachRoot : Boolean =false) : View =
    LayoutInflater.from(context).inflate(resource,this,attachRoot)