package com.example.myproject.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : BaseContract.Presenter, B : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    AppCompatActivity(), BaseContract.View {

    abstract val presenter: T
    protected lateinit var binding: B

    override fun networkError(errorMessage: String) {
        Toast.makeText(this, "error : $errorMessage", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}
