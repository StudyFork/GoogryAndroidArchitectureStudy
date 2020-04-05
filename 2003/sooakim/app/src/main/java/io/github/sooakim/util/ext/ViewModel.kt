package io.github.sooakim.util.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.sooakim.util.NotNullMutableLiveData

fun <E : Any> ViewModel.liveOf(defaultValue: E? = null) = lazy {
    MutableLiveData<E>(defaultValue)
}

fun <E : Any> ViewModel.notNullLiveOf(defaultValue: E) = lazy {
    NotNullMutableLiveData(defaultValue)
}