package com.example.androidarchitecturestudy.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<presenter : BaseContract.Presenter, binding : ViewDataBinding>(@LayoutRes val layout: Int) :
    AppCompatActivity(layout), BaseContract.View {

    lateinit var binding: binding

    override fun showResultEmpty(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
    }
}