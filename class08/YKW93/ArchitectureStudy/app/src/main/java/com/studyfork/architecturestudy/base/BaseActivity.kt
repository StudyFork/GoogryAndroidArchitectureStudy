package com.studyfork.architecturestudy.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import com.studyfork.architecturestudy.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    protected val viewModel: VM by lazy {
        getViewModelInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.vm, viewModel)

        viewModel.observableToastMessage.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(
                    baseContext,
                    viewModel.observableToastMessage.get(),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    abstract fun getViewModelInstance(): VM
}