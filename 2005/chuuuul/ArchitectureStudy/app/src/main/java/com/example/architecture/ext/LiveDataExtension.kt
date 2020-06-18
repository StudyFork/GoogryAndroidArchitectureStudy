package com.example.architecture.ext

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T : Any> MutableLiveData<T>.createDefault(defaultValue: T): MutableLiveData<T> {
    this.value = defaultValue
    return this
}

fun <T> LiveData<T>.debounce(duration: Long) = MediatorLiveData<T>().also { mld ->
    val source = this
    val handler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        mld.value = source.value
    }

    mld.addSource(source) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, duration)
    }
}