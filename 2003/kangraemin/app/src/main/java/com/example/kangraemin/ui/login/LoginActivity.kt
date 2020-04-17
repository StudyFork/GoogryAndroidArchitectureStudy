package com.example.kangraemin.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.databinding.ActivityLoginBinding
import com.example.kangraemin.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : KangBaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this

        binding.vm = loginViewModel

        loginViewModel.loginError.observe(this, Observer {
            binding.layoutPw.error = getString(R.string.login_fail)
        })

        loginViewModel.loginSuccess.observe(this, Observer {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        loginViewModel.idIsEmpty.observe(this, Observer {
            if (it) {
                binding.layoutId.error = getString(R.string.login_error_id_empty)
            } else {
                binding.layoutId.error = null
            }
        })

        loginViewModel.pwIsEmpty.observe(this, Observer {
            if (it) {
                binding.layoutPw.error = getString(R.string.login_error_pw_empty)
            } else {
                binding.layoutPw.error = null
            }
        })

        loginViewModel.addAuthSuccess.observe(this, Observer {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        loginViewModel.addAuthFail.observe(this, Observer {
            toast(getString(R.string.login_error_add_auth_toast_message))
        })
    }
}
