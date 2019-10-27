package com.ironelder.androidarchitecture.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        doStart()
    }

    override fun onRestart() {
        super.onRestart()
        doRestart()
    }

    override fun onResume() {
        super.onResume()
        doResume()
    }

    override fun onPause() {
        super.onPause()
        doPause()
    }

    override fun onStop() {
        super.onStop()
        doStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        doDestroy()
    }

    open fun doCreate(savedInstanceState: Bundle?){}
    open fun doStart() {}
    open fun doRestart() {}
    open fun doResume() {}
    open fun doPause() {}
    open fun doStop() {}
    open fun doDestroy() {}

}