package com.nanamare.mac.sample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.ui.MainActivity
import com.nanamare.mac.sample.ui.ProgressDialogFragment

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var dialog: ProgressDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = ProgressDialogFragment()
    }

    override fun showLoadingDialog() {
        dialog.show(supportFragmentManager, MainActivity.PROGRESS_DIALOG_FRAGMENT)
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

}
