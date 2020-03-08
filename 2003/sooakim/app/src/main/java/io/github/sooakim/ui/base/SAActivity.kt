package io.github.sooakim.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.github.sooakim.ui.application.SAApplication
import io.reactivex.disposables.CompositeDisposable

abstract class SAActivity : AppCompatActivity() {
    protected val compositeDisposable: CompositeDisposable by lazy{
        CompositeDisposable()
    }
    protected open val commonProgressView: View? = null
    protected val application: SAApplication?
        get() {
            return (applicationContext as? SAApplication)
        }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun showLoading() {
        commonProgressView?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        commonProgressView?.visibility = View.INVISIBLE
    }

    @Throws(IllegalStateException::class)
    fun requireApplication(): SAApplication {
        if (applicationContext is SAApplication) {
            return application!!
        } else {
            throw IllegalStateException("applicationContext is not instance of SAApplication")
        }
    }
}