package com.byiryu.study.ui.base

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    protected open val progressBar: View? = null

    fun showMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showMsg(@StringRes res: Int) {
        showMsg(getString(res))
    }

    fun showLoading() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar?.visibility = View.INVISIBLE
    }

    fun goActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

}