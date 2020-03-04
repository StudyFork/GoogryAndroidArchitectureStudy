package io.github.sooakim.ui.base

import io.github.sooakim.network.model.SAModel

interface SAViewHolderLifecycle<T : SAModel> {
    fun onBind(item: T)

    fun onRecycle()
}