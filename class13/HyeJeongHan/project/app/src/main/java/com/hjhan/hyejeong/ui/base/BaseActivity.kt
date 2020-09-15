package com.hjhan.hyejeong.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseContract.Presenter>(layoutResId: Int)
    : AppCompatActivity(layoutResId), BaseContract.View {

    abstract val presenter: T

    override fun showError() {
        Toast.makeText(this, "알 수 없는 에러", Toast.LENGTH_SHORT).show()
    }
}