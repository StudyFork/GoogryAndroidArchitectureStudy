package io.github.sooakim.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.github.sooakim.BR
import io.github.sooakim.ui.application.SAApplication

abstract class SAActivity<VDB : ViewDataBinding, VM : SAViewModel<*>, S : SAState>(
    @LayoutRes
    protected val layoutResId: Int = 0
) : AppCompatActivity() {
    protected lateinit var viewDataBinding: VDB
    protected abstract val viewModel: VM
    protected open val viewModelBrId: Int = BR.viewModel
    protected val application: SAApplication?
        get() = (applicationContext as? SAApplication)

    init {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                        viewModel.create()
                    }
                    Lifecycle.Event.ON_START -> {
                        viewModel.start()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        viewModel.resume()
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        viewModel.pause()
                    }
                    Lifecycle.Event.ON_STOP -> {
                        viewModel.stop()
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                        lifecycle.removeObserver(this)
                    }
                    else -> {
                    }
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutResId != 0) {
            viewDataBinding = DataBindingUtil.setContentView<VDB>(this, layoutResId).apply {
                lifecycleOwner = this@SAActivity
                setVariable(viewModelBrId, viewModel)
            }
        }

        viewModel.state.observe(this, Observer {
            if (it != null && !it.isConsumed) {
                it.isConsumed = true

                @Suppress("UNCHECKED_CAST")
                onState(it.event as S)
            }
        })
    }

    protected open fun onState(newState: S) {

    }

    @Throws(IllegalStateException::class)
    fun requireApplication(): SAApplication {
        if (applicationContext is SAApplication) {
            return application!!
        } else {
            throw IllegalStateException("applicationContext is not instance of SAApplviication")
        }
    }
}