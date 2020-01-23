package com.studyfork.architecturestudy.base

import io.reactivex.disposables.Disposable

interface BaseContract {
    interface View {
        fun setLoadingVisible(visible: Boolean)
    }

    interface Presenter {
        fun subscribe(disposable: Disposable)
        fun unsubscribe()
    }
}