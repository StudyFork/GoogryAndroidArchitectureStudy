package app.ch.study.core

import android.util.Log
import androidx.lifecycle.ViewModel
import app.ch.study.data.remote.api.WebApiDefine
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

open class BaseViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun handleError(e: Throwable) : String {
        if (e is HttpException) {
            // dump e.response().errorBody()

            val code = e.code()
            WebApiDefine.showErrorLog(code)
        } else {
            Log.e("HttpErrorHandler", e.toString())
        }

        return ((e as? Exception)?.message)?:""
    }

}