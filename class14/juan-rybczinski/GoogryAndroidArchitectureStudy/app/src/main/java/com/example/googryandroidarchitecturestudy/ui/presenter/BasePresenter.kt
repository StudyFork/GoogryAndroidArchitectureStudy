package com.example.googryandroidarchitecturestudy.ui.presenter

import android.webkit.URLUtil
import com.example.googryandroidarchitecturestudy.domain.UrlResource
import com.example.googryandroidarchitecturestudy.ui.contract.BaseContract

abstract class BasePresenter(
    private val view: BaseContract.View
) : BaseContract.Presenter {
    override fun selectUrlItem(item: UrlResource) {
        if (URLUtil.isValidUrl(item.link)) {
            view.showUrlResource(item)
        }
    }
}