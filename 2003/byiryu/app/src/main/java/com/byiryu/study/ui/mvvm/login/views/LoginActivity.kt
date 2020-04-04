package com.byiryu.study.ui.mvvm.login.views

import android.os.Bundle
import androidx.databinding.Observable
import com.byiryu.study.databinding.ActivityLoginBinding

import com.byiryu.study.ui.mvvm.base.views.BaseActivity
import com.byiryu.study.ui.mvvm.login.viewmodel.LoginViewModel
import com.byiryu.study.ui.mvvm.main.views.MainActivity

class LoginActivity : BaseActivity(){

    private val viewModel by lazy {
        LoginViewModel(getBRApplication().repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            vm = viewModel
        }
        viewModel.loginStatus.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(viewModel.loginStatus.get()!!.first){
                    goActivity(MainActivity::class.java)
                    finish()
                }else{
                    showMsg(viewModel.loginStatus.get()!!.second)
                }
            }

        })

        setContentView(binding.root)
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}