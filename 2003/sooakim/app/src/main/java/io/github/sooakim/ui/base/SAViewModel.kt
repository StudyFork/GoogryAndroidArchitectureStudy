package io.github.sooakim.ui.base

import io.reactivex.disposables.CompositeDisposable

open class SAViewModel<N : SANavigator>(
    protected val navigator: N
) : SAViewModelLifecycle {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}