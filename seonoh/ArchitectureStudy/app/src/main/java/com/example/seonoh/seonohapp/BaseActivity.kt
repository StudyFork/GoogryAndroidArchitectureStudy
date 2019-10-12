package com.example.seonoh.seonohapp

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.seonoh.seonohapp.contract.BaseContract

abstract class BaseActivity<P : BaseContract.Presenter, B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity(), BaseContract.View<P> {

    private lateinit var toast: Toast
    protected lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        binding = DataBindingUtil.setContentView(this,layoutRes)
    }

    override fun showToast() {
        if (toast.view.isShown) {
            finish()
        } else {
            toast.show()
        }
    }

    override fun onBackPressed() {
        showToast()
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}