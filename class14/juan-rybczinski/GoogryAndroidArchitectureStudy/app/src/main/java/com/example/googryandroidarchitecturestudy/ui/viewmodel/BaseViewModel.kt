package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.webkit.URLUtil
import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.domain.UrlResource

abstract class BaseViewModel {
    val selectedUrl = ObservableField("")

    fun checkUrl(item: UrlResource) {
        if (URLUtil.isValidUrl(item.link)) {
            selectedUrl.set(item.link)
            // TODO: 2020/11/30 Check same url case?
        }
    }
}