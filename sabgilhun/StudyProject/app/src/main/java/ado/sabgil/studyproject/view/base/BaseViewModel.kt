package ado.sabgil.studyproject.view.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposable = CompositeDisposable()

    fun onDestroy() {
        disposable.clear()
    }

}