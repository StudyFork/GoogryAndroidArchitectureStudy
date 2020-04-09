package com.byiryu.study.ui.mvvm.login.views

import android.os.Bundle
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.byiryu.study.databinding.ActivityLoginBinding

import com.byiryu.study.ui.mvvm.base.views.BaseActivity
import com.byiryu.study.ui.mvvm.login.viewmodel.LoginViewModel
import com.byiryu.study.ui.mvvm.main.views.MainActivity

class LoginActivity : BaseActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this, getBRApplication().viewModelFactory)[LoginViewModel::class.java]
        val binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@LoginActivity
            vm = viewModel

            viewModel.loginStatus.observe(this@LoginActivity, Observer {
                it?.run{
                    if(first){
                        goActivity(MainActivity::class.java)
                        finish()
                    }else{
                        showMsg(second)
                    }
                }
            })
        }


        setContentView(binding.root)
    }
}