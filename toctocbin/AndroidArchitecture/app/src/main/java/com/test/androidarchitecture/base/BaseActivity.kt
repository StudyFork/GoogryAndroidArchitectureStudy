package com.test.androidarchitecture.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BaseContract.Presenter>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity(), BaseContract.View<P> {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
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