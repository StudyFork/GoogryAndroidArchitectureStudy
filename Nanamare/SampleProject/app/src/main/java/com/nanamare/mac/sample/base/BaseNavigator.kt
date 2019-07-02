package com.nanamare.mac.sample.base

import android.os.Bundle

interface BaseNavigator {
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun goToFragment(cls: Class<*>, args: Bundle?)
}
