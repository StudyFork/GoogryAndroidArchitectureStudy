package com.example.study.ui.base

import com.example.study.data.model.Movie
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface BaseContract {

    //공용으로 쓰일 View 함수들
    interface View {
        fun showProgress()
        fun hideProgress()
    }

    //공용으로 쓰일 Presenter 함수들
    interface Presenter {
        fun addDisposable(disposable: Disposable)
        fun clearDisposable()
    }
}
