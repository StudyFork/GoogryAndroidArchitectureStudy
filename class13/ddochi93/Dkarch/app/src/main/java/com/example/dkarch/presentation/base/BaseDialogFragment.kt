package com.example.dkarch.presentation.base

import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment<T : BaseContract.Presenter> : DialogFragment(),
    BaseContract.View {

    abstract val presenter: T
    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        presenter.onDestroy()
        compositeDisposable.clear()
        super.onDestroyView()
    }
}
