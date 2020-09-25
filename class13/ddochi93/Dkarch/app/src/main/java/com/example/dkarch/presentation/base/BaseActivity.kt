package com.example.dkarch.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : BaseContract.Presenter, U : ViewDataBinding>(@LayoutRes private val contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId),
    BaseContract.View {

    abstract val presenter: T
    protected lateinit var binding: U

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, contentLayoutId)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
