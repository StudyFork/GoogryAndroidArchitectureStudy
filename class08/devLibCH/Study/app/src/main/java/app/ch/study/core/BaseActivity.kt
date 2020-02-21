package app.ch.study.core

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.ch.study.BR

abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val resId: Int) :
    AppCompatActivity(resId) {

    abstract val vm: VM
    abstract val pbLoading: FrameLayout?

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, resId)
        binding.setVariable(BR.vm, vm)
    }

    fun showLoading() {
        pbLoading?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        pbLoading?.visibility = View.GONE
    }

    override fun onDestroy() {
        vm.clearDisposable()
        super.onDestroy()
    }
}