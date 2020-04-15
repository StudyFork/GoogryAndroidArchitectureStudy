package com.byiryu.study.ui.mvvm.login.views

import android.os.Bundle
import androidx.lifecycle.Observer
import com.byiryu.study.databinding.ActivityLoginBinding
import com.byiryu.study.ui.mvvm.base.views.BaseActivity
import com.byiryu.study.ui.mvvm.login.viewmodel.LoginViewModel
import com.byiryu.study.ui.mvvm.main.views.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity(){

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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