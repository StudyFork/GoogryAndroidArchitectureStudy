package com.studyfork.architecturestudy.base

import androidx.annotation.NonNull
import androidx.databinding.ObservableField

abstract class BaseViewModel {
    var observableToastMessage: ObservableField<String> = ObservableField("")

    fun showToast(@NonNull message: String) {
        observableToastMessage.set(message)
    }
}