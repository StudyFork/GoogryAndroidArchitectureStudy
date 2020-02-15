package app.ch.study.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel {

    private val compositeDisposable = CompositeDisposable()

    operator fun CompositeDisposable.plus(disposable: Disposable) {
        add(disposable)
    }

    fun Disposable.addToDisposable(): Disposable
            = apply { compositeDisposable.add(this) }

    fun clearDisposable() = compositeDisposable.clear()

}