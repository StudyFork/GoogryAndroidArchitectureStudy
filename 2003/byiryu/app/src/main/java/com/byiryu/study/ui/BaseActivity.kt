package com.byiryu.study.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {


    fun showMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showMsg(@StringRes res: Int) {
        showMsg(getString(res))
    }

}