package app.ch.study.core

import android.util.Log
import app.ch.study.data.remote.api.WebApiDefine
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

abstract class BasePresenter : BaseContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    operator fun CompositeDisposable.plus(disposable: Disposable) {
        add(disposable)
    }

    fun Disposable.addTo(androidDisposable: CompositeDisposable): Disposable
            = apply { androidDisposable.add(this) }

    override fun clearDisposable() =
        compositeDisposable.clear()

    override fun handleError(e: Throwable): String {
        if (e is HttpException) {
            val code = e.code()
            WebApiDefine.showErrorLog(code)
        } else {
            Log.e("HttpErrorHandler", e.toString())
        }

        return ((e as? Exception)?.message) ?: ""
    }

}