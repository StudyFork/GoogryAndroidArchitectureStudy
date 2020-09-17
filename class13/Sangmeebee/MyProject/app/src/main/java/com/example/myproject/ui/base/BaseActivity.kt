package com.example.myproject.ui.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseContract.Presenter>(@LayoutRes val layoutId: Int) :
    AppCompatActivity(layoutId), BaseContract.View {

    abstract val presenter: T

    override fun networkError(errorMessage: String) {
        Toast.makeText(this, "error : $errorMessage", Toast.LENGTH_LONG).show()
    }

}
