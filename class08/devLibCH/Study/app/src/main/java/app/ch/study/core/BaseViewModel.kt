package app.ch.study.core

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    operator fun CompositeDisposable.plus(disposable: Disposable) {
        add(disposable)
    }

    protected fun Disposable.addToDisposable(): Disposable
            = apply { compositeDisposable.add(this) }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}