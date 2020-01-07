package com.jay.architecturestudy.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

inline fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}