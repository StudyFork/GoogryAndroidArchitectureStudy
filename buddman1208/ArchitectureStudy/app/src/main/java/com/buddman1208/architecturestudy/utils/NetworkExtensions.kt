package com.buddman1208.architecturestudy.utils

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


private val DEFAULT_ERROR_HANDLE: (Throwable) -> Unit = {

    if (it.message?.isNotBlank() == true)
        Log.e("DEFAULT_ERROR_HANDLE", it.message)
}


fun <T> Observable<T>.subscribeOnIO(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.onUI(onNext: (T) -> Unit): Disposable {
    return this.onUI(onNext, DEFAULT_ERROR_HANDLE)
}

fun <T> Observable<T>.onUI(onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {

    return this
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)

}