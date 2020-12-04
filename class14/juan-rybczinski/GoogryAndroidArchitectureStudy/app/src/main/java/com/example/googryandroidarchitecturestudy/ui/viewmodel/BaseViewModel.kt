package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.webkit.URLUtil
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.domain.UrlResource

abstract class BaseViewModel : BaseObservable() {
    val selectedUrl = ObservableField("")
    val hideKeyboardEvent = ObservableField<Unit>()
    val showInvalidUrlEvent = ObservableField<Unit>()

    fun selectUrlItem(item: UrlResource) {
        if (URLUtil.isValidUrl(item.link)) {
            selectedUrl.set(item.link)
            selectedUrl.notifyChange()
        } else {
            showInvalidUrlEvent.notifyChange()
        }
    }
}