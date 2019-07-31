package org.study.kotlin.androidarchitecturestudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<B : ViewDataBinding, VM: ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment(), BaseView {

    protected lateinit var binding: B
    abstract val viewModel: VM
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            binding.lifecycleOwner = this@BaseFragment
            binding.setVariable(BR.vm, viewModel)
        }
    }

    override fun showErrorToast(errorMessage: Throwable) {

    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }
}