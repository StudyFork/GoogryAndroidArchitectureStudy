package com.byiryu.study.ui.mvvm.base.views

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.byiryu.study.ui.BRApplication

abstract class BaseActivity : AppCompatActivity() {
    fun showMsg(res: Int) {
        showMsg(getString(res))
    }

    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun goActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    fun goActivity(intent: Intent) {
        startActivity(intent)
    }

    fun getBRApplication(): BRApplication {
        return applicationContext as BRApplication
    }

}