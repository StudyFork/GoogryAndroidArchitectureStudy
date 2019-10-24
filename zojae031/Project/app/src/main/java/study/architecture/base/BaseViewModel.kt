package study.architecture.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    protected lateinit var disposable: Disposable
    open fun onPause() {
        disposable.dispose()
        compositeDisposable.clear()
    }

    abstract fun onResume()
}