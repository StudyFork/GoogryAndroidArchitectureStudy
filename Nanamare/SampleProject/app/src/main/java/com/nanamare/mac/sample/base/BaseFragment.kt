package com.nanamare.mac.sample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment(), BaseNavigator {

    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun goToFragment(cls: Class<*>, args: Bundle?) {
        (activity as? BaseActivity<*>)?.goToFragment(cls, args)
    }

    override fun showLoadingDialog() {
        (activity as? BaseActivity<*>)?.showLoadingDialog()
    }

    override fun hideLoadingDialog() {
        (activity as? BaseActivity<*>)?.hideLoadingDialog()
    }
    
}