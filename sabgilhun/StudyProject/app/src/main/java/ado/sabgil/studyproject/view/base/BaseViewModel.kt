package ado.sabgil.studyproject.view.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposables = CompositeDisposable()

    fun onDestroy() {
        disposables.clear()
    }

}