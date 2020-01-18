package com.siwon.prj.common.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(@get:LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    lateinit var binding: B
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    fun showToast(msg: String) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
            toast?.show()
        } else {
            toast?.run {
                setText(msg)
                duration = Toast.LENGTH_SHORT
                show()
            }
        }
    }
}