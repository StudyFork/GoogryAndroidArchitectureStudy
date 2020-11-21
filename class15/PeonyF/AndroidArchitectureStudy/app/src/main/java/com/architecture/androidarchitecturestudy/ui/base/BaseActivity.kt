package com.architecture.androidarchitecturestudy.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View {
    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}