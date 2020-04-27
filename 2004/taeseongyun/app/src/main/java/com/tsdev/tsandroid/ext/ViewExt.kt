package com.tsdev.tsandroid.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.util.widget.DebounceClickListener
import com.tsdev.tsandroid.util.widget.OnClickListener
import io.reactivex.rxjava3.disposables.CompositeDisposable

fun Context.showToast(durationTime: Int) =
    Toast.makeText(
        this,
        R.string.occur_error_toast, durationTime
    ).show()

fun View.setOnDebounceClickListener(listener: OnClickListener) {
    setOnClickListener(DebounceClickListener(CompositeDisposable()) {
        it.run(listener)
    })
}