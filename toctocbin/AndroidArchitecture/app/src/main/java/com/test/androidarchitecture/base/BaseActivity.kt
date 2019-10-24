package com.test.androidarchitecture.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
    abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        vm.toastMessage.addOnPropertyChangedCallback(object  : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@BaseActivity, vm.toastMessage.get(), Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroy() {
        vm.clearDisposable()
        super.onDestroy()
    }

}