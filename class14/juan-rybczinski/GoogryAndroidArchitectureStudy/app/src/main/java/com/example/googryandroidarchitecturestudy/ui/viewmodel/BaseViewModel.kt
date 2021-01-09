package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.UrlResource

abstract class BaseViewModel : ViewModel() {
    private val _selectedUrl = MutableLiveData<String>()
    val selectedUrl: LiveData<String>
        get() = _selectedUrl

    val hideKeyboardEvent = MutableLiveData<Unit>()

    private val _showInvalidUrlEvent = MutableLiveData<Unit>()
    val showInvalidUrlEvent: LiveData<Unit>
        get() = _showInvalidUrlEvent

    fun selectUrlItem(item: UrlResource) {
        if (URLUtil.isValidUrl(item.link)) {
            _selectedUrl.value = item.link
        } else {
            _showInvalidUrlEvent.value = Unit
        }
    }
}