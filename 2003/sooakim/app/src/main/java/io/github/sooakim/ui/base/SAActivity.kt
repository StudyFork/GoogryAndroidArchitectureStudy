package io.github.sooakim.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.github.sooakim.ui.application.SAApplication

abstract class SAActivity<Presenter : SABasePresenter> : AppCompatActivity(), SABaseView {
    protected abstract val presenter: Presenter
    protected open val commonProgressView: View? = null
    protected val application: SAApplication?
        get() = (applicationContext as? SAApplication)

    init {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                        presenter.create()
                    }
                    Lifecycle.Event.ON_START -> {
                        presenter.start()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        presenter.resume()
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        presenter.pause()
                    }
                    Lifecycle.Event.ON_STOP -> {
                        presenter.stop()
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                        presenter.destroy()
                        lifecycle.removeObserver(this)
                    }
                    else -> {
                    }
                }
            }
        })
    }

    override fun showLoading() {
        commonProgressView?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
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