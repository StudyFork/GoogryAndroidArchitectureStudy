package com.studyfirstproject

import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity() {

    fun handlingError(msg: String, reason: String?) {
        toast(msg)
        Log.e(localClassName, reason ?: "No error message")
    }
}