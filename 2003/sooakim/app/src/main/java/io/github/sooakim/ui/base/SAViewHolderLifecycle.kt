package io.github.sooakim.ui.base

import io.github.sooakim.ui.model.SAViewModel

interface SAViewHolderLifecycle<T : SAViewModel> {
    fun onBind(item: T)

    fun onRecycle()
}