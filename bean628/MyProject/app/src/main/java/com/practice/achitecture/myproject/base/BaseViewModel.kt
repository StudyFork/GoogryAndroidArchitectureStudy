package com.practice.achitecture.myproject.base

import kotlin.properties.Delegates

open class BaseViewModel {

    var progressBarIsShowing: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        if (newValue) {
            hideSoftKeyboardObserver?.invoke()
        }
        showProgressBarObserver?.invoke(newValue)
    }

    var showProgressBarObserver: ((Boolean) -> Unit)? = null

    var hideSoftKeyboardObserver: (() -> Unit)? = null

    var stringMessageId: Int by Delegates.observable(-999) { property, oldValue, newValue ->
        stringMessageIdObserver?.invoke(newValue)
    }

    var stringMessageIdObserver: ((Int) -> Unit)? = null

}