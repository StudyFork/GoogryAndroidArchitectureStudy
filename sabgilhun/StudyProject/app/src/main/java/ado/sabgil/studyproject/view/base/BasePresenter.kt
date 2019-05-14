package ado.sabgil.studyproject.view.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View> constructor(
    protected val view: V
) : BaseContract.Presenter {

    protected val disposables = CompositeDisposable()

    override fun unSubscribe() {
        disposables.clear()
    }

}