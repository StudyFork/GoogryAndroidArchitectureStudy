package com.hhi.myapplication.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View {
    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}