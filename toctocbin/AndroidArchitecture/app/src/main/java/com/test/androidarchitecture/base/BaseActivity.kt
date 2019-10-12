package com.test.androidarchitecture.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<P : BaseContract.Presenter, B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity(), BaseContract.View<P> {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        start()
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    abstract fun start()
}