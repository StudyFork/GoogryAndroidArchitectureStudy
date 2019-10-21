package com.jake.archstudy.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import com.jake.archstudy.ext.toast

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel.toast.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                toast(viewModel.toast.get() ?: return)
            }
        })
    }

}