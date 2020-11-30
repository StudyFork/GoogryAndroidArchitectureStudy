package com.jay.aas.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val loading = ObservableField(false)

    fun showLoading() {
        loading.set(true)
    }

    fun hideLoading() {
        loading.set(false)
    }

}