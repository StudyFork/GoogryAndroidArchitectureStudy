package com.jake.archstudy.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jake.archstudy.ext.toast

abstract class BaseActivity<B : ViewDataBinding, P : BaseContract.Presenter>(
    @LayoutRes
    val layoutRes: Int
) : AppCompatActivity(),
    BaseContract.View<P> {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        presenter.start()
    }

    override fun showToast(
        @StringRes
        stringResId: Int,
        duration: Int
    ) {
        toast(stringResId, duration)
    }

}