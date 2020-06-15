package com.example.architecture.ext

import androidx.lifecycle.MutableLiveData

fun <T : Any> MutableLiveData<T>.createDefault(defaultValue: T): MutableLiveData<T> {
    this.value = defaultValue
    return this
}
