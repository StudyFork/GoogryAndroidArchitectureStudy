package com.practice.achitecture.myproject.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.makeToast

abstract class BaseActivity<DB : ViewDataBinding>(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(), BaseContract.View {

    open var progressBar: ProgressBar? = null

    open val binding by lazy {
        DataBindingUtil.setContentView(this, contentLayoutId) as DB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar = binding.root.findViewById(R.id.progress_bar)
    }

    override fun showToast(message: String) {
        this.makeToast(message)
    }

    override fun showToast(stringResId: Int) {
        this.makeToast(stringResId)
    }

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar?.visibility = View.GONE
    }
}