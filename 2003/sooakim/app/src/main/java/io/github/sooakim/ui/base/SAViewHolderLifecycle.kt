package io.github.sooakim.ui.base

import io.github.sooakim.ui.model.SAPresentation

interface SAViewHolderLifecycle<T : SAPresentation> {
    fun onBind(item: T)

    fun onRecycle()
}