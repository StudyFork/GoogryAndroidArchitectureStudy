package com.nanamare.mac.sample.presenter

import android.os.Bundle

interface BaseView {
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun goToFragment(cls: Class<*>, args: Bundle?)
}
