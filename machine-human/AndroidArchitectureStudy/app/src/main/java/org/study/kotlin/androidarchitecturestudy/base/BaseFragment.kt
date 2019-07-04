package org.study.kotlin.androidarchitecturestudy.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int
) : Fragment(),BaseView {

    protected lateinit var binding: B
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun showErrorToast(errorMessage: Throwable) {

    }
}