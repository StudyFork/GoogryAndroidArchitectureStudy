package com.jay.architecturestudy.util

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> singleIoMainThread(): SingleTransformer<T, T> {
    return SingleTransformer { upstream -> upstream
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
    }
}