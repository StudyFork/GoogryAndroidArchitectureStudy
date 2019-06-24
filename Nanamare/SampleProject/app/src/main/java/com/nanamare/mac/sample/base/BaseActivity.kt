package com.nanamare.mac.sample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.ui.ProgressDialogFragment

abstract class BaseActivity(
    @LayoutRes val layoutResId : Int
) : AppCompatActivity(), BaseView {

    abstract val presenter: BasePresenter

    private val dialog by lazy { ProgressDialogFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        presenter.start()

    }

    override fun showLoadingDialog() {
        dialog.show(supportFragmentManager, PROGRESS_DIALOG_FRAGMENT)
    }

    override fun hideLoadingDialog() {
        dialog.dismiss()
    }

    override fun goToFragment(cls: Class<*>, args: Bundle?) {
        try {
            val fragment = cls.newInstance() as Fragment
            fragment.arguments = args
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fl_content, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        presenter.close()
        super.onDestroy()
    }


    companion object {
        const val KET_MARKET_LIST = "KET_MARKET_LIST"
        const val PROGRESS_DIALOG_FRAGMENT = "PROGRESS_DIALOG_FRAGMENT"
    }

}
