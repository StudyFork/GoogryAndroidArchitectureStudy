package com.nanamare.mac.sample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int
) : Fragment(), BaseView {

    open val coinPresenter: BasePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        coinPresenter?.start()
    }

    override fun goToFragment(cls: Class<*>, args: Bundle?) {
        (activity as? BaseActivity)?.goToFragment(cls, args)
    }

    override fun showLoadingDialog() {
        (activity as? BaseActivity)?.showLoadingDialog()
    }

    override fun hideLoadingDialog() {
        (activity as? BaseActivity)?.hideLoadingDialog()
    }

}