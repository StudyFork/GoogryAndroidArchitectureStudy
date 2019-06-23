package com.nanamare.mac.sample.base

import android.os.Bundle

interface BaseView {
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun goToFragment(cls: Class<*>, args: Bundle?)
}
