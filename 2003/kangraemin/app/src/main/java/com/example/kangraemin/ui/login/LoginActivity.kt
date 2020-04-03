package com.example.kangraemin.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.databinding.ActivityLoginBinding
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.ui.login.LoginViewModel.AddAuthResponse.ADD_AUTH_ERROR
import com.example.kangraemin.ui.login.LoginViewModel.AddAuthResponse.ADD_AUTH_SUCCESS
import com.example.kangraemin.ui.login.LoginViewModel.LogInResponse.LOGIN_ERROR
import com.example.kangraemin.ui.login.LoginViewModel.LogInResponse.LOGIN_SUCCESS
import com.example.kangraemin.ui.main.MainActivity

class LoginActivity : KangBaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = LoginViewModel(
            authRepository = AuthRepository(
                authLocalDataSource = AuthLocalDataSourceImpl(
                    AppDatabase.getInstance(context = this).authDao()
                )
            )
        )

        binding.vm = loginViewModel

        loginViewModel.loginResponse.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (loginViewModel.loginResponse.get()) {
                    LOGIN_SUCCESS -> {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    LOGIN_ERROR -> {
                        binding.layoutPw.error = getString(R.string.login_fail)
                    }
                }
            }
        })

        loginViewModel.idIsEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (loginViewModel.idIsEmpty.get()) {
                    binding.layoutId.error = getString(R.string.login_error_id_empty)
                } else {
                    binding.layoutId.error = null
                }
            }
        })

        loginViewModel.pwIsEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (loginViewModel.pwIsEmpty.get()) {
                    binding.layoutPw.error = getString(R.string.login_error_pw_empty)
                } else {
                    binding.layoutPw.error = null
                }
            }
        })

        loginViewModel.addAuthResponse.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (loginViewModel.addAuthResponse.get()) {
                    ADD_AUTH_SUCCESS -> {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    ADD_AUTH_ERROR -> {
                        toast(getString(R.string.login_error_add_auth_toast_message))
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        loginViewModel.onDestroy()
        super.onDestroy()
    }
}
