package com.example.study.util.base

import com.example.study.data.model.Movie
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface BaseContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun hideKeyboard()
    }

    interface Presenter {
        fun addDisposable(disposable: Disposable)
        fun clearDisposable()
    }
}
