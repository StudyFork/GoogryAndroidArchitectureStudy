package com.example.seonoh.seonohapp

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity< B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity(){

    private lateinit var toast: Toast
    protected lateinit var binding : B
    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        toast = Toast.makeText(this,"뒤로가기를 한번 더 누르면 앱이 종료됩니다.",Toast.LENGTH_LONG)
        binding = DataBindingUtil.setContentView(this,layoutRes)
    }

    private fun showToast() {
        if (toast.view.isShown) {
            finish()
        } else {
            toast.show()
        }
    }

    override fun onBackPressed() {
        showToast()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}