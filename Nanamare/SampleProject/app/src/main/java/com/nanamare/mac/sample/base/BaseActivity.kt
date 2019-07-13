package com.nanamare.mac.sample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.ui.ProgressDialogFragment

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutResId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    private var dialog: ProgressDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    fun showLoadingDialog() {
        if(dialog == null) {
            dialog = ProgressDialogFragment().apply {
                show(supportFragmentManager, PROGRESS_DIALOG_FRAGMENT)
            }
        }
    }

    fun hideLoadingDialog() {
        if(dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }

    fun goToFragment(cls: Class<*>, args: Bundle?) {
        try {
            val fragment = cls.newInstance() as Fragment
            fragment.arguments = args
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fl_content, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val KET_MARKET_LIST = "KET_MARKET_LIST"
        const val PROGRESS_DIALOG_FRAGMENT = "PROGRESS_DIALOG_FRAGMENT"
    }

}
