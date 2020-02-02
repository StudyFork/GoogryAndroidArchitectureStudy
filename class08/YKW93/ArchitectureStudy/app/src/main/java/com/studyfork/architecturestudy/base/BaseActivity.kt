package com.studyfork.architecturestudy.base

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, P : BasePresenter>(
    @LayoutRes layoutResId: Int
) : AppCompatActivity(), BaseContract.View {

    abstract val presenter: P
    abstract val progressBar: ProgressBar

    protected val binding: B by lazy {
        DataBindingUtil.setContentView<B>(this, layoutResId)
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}