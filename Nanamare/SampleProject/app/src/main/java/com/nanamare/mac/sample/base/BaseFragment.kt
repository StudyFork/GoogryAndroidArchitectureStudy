package com.nanamare.mac.sample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nanamare.mac.sample.presenter.BaseView

open class BaseFragment : Fragment() , BaseView {

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