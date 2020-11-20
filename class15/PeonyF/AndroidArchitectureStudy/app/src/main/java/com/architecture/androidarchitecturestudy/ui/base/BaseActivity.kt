package com.architecture.androidarchitecturestudy.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity<P : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View {
    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}