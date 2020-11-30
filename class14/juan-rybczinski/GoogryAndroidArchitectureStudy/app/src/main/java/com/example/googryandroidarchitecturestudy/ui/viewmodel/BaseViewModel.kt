package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.webkit.URLUtil
import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.domain.UrlResource

abstract class BaseViewModel {
    val selectedUrl = ObservableField("")
    val hideKeyboardEvent = ObservableField<Unit>()

    fun selectUrlItem(item: UrlResource) {
        if (URLUtil.isValidUrl(item.link)) {
            selectedUrl.set(item.link)
            selectedUrl.notifyChange()
        } else {
            selectedUrl.set(INVALID_URL)
        }
    }

    companion object {
        const val INVALID_URL = "INVALID_URL"
    }
}