package study.architecture.myarchitecture.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(
    @LayoutRes resource: Int, attachRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(resource, this, attachRoot)