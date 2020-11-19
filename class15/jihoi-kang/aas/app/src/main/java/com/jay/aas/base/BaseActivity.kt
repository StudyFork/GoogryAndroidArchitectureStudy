package com.jay.aas.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding, P : BaseContract.Presenter> : AppCompatActivity(),
    BaseContract.View {

    protected lateinit var binding: B
    protected var progressBar: ProgressBar? = null

    protected abstract val presenter: P

    protected abstract fun inflateViewBinding(inflater: LayoutInflater): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        progressBar = null
        super.onDestroy()
    }

    override fun showProgressBar() {
        progressBar?.let { it.isVisible = true }
    }

    override fun hideProgressBar() {
        progressBar?.let { it.isGone = true }
    }
}