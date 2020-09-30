package com.hong.architecturestudy.ext

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.hong.architecturestudy.ui.main.MainViewModel
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit


@BindingAdapter("onClickQuery", "bindVm")
fun View.setOnClickQuery(query: String, vm: MainViewModel? = null) {
    setOnClickListener {
        vm?.let {
            it.query.set(query)
            it.searchMovieList()
            it.visibleChange()

        }
    }
}

@BindingAdapter("onClickOpenLink")
fun View.onClick(url: String) {
    setOnClickListener {
        ContextCompat.startActivity(
            context, Intent(Intent.ACTION_VIEW, Uri.parse(url)),
            null
        )
    }
}

@BindingAdapter("throttleClick")
fun View.setThrottleClick(listener: View.OnClickListener) {
    RxView.clicks(this)
        .throttleFirst(1000L, TimeUnit.MILLISECONDS)
        .subscribe { listener.onClick(this) }
}
