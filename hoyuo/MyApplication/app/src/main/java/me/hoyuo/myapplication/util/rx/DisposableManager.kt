package me.hoyuo.myapplication.util.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class DisposableManager {
    private var openDisposables = CompositeDisposable()

    val isDisposed: Boolean get() = openDisposables.isDisposed

    operator fun plusAssign(disposable: Disposable) = add(disposable)

    fun add(disposable: Disposable) {
        if (isDisposed) {
            Timber.e("Can't add disposable")
            disposable.dispose()
            return
        }
        openDisposables.add(disposable)
    }

    fun add(vararg disposables: Disposable) = disposables.forEach { add(it) }

    fun dispose() = openDisposables.dispose()

    fun refresh() {
        if (!isDisposed) {
            if (openDisposables.size() > 0) {
                Timber.w("DisposableManager has active disposables.")
            }
            dispose()
        }
        openDisposables = CompositeDisposable()
    }

    fun refreshIfNecessary() {
        if (isDisposed) openDisposables = CompositeDisposable()
    }

    operator fun minusAssign(disposable: Disposable) = remove(disposable)

    fun remove(vararg disposables: Disposable) = disposables.forEach { openDisposables.remove(it) }

    fun remove(disposables: Collection<Disposable>) = disposables.forEach {
        openDisposables.remove(it)
    }
}
