package com.buddman1208.architecturestudy.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun String.removeHtmlBoldTags(): String {
    return this
        .replace("<b>", "")
        .replace("</b>", "")
}

fun Disposable.addToCompositeDisposable(target : CompositeDisposable) = target.add(this)