package com.example.androidarchitecturestudy.ui.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<presenter : BaseContract.Presenter>(@LayoutRes val layout: Int) :
    AppCompatActivity(layout), BaseContract.View {

    override fun showResultEmpty(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}