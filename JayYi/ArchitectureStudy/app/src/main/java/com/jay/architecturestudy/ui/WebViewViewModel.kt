package com.jay.architecturestudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewViewModel(
    landingUrl: String
) : ViewModel() {

    private val _url: MutableLiveData<String> = MutableLiveData()
    val url: LiveData<String>
        get() = _url

    init {
        _url.value = landingUrl
    }
}