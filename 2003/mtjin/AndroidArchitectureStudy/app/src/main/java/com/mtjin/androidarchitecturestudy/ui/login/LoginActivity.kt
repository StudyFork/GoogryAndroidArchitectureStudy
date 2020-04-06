package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.base.BaseActivity
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.databinding.ActivityLoginBinding
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchActivity
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var loginLocalDataSource: LoginLocalDataSource
    private lateinit var loginRepository: LoginRepository
    private val viewModel: LoginViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel(loginRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initViewModelCallback()
    }

    private fun inject() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        preferenceManager = PreferenceManager(this)
        loginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        loginRepository = LoginRepositoryImpl(loginLocalDataSource)
        binding.vm = viewModel
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isIdEmpty.observe(this@LoginActivity, Observer {
                showIdEmptyError()
            })
            isPwEmpty.observe(this@LoginActivity, Observer {
                showPwEmptyError()
            })
            loginErrorMsg.observe(this@LoginActivity, Observer {
                showToast(getString(R.string.id_pw_not_correct_error_msg))
            })
            successLogin.observe(this@LoginActivity, Observer {
                goMovieSearch()
            })
        }
    }

    private fun showIdEmptyError() {
        binding.etId.error = getString(R.string.id_empty_error_msg)
    }

    private fun showPwEmptyError() {
        binding.etPw.error = getString(R.string.pw_empty_error_msg)
    }

    private fun goMovieSearch() {
        showToast(getString(R.string.login_success_msg))
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }


}
