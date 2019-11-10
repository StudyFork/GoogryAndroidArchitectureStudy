package com.jake.archstudy.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast

    fun toast(message: String) {
        _toast.value = message
    }
}