package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.databinding.ActivityLoginBinding
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchActivity
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initViewModelCallback()
    }

    private fun inject() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        viewModel = LoginViewModel(loginRepository)
        binding.vm = viewModel
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isIdEmpty.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showIdEmptyError()
                    isIdEmpty.set(false)
                }

            })
            isPwEmpty.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showPwEmptyError()
                    isPwEmpty.set(false)
                }

            })
            loginErrorMsg.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showLoginError()
                    loginErrorMsg.set(false)
                }

            })
            successLogin.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    goMovieSearch()
                    successLogin.set(false)
                }

            })
        }
    }


    fun showLoginError() {
        Toast.makeText(this, getString(R.string.id_pw_not_correct_error_msg), Toast.LENGTH_SHORT)
            .show()
    }

    fun showIdEmptyError() {
        binding.etId.error = getString(R.string.id_empty_error_msg)
    }

    fun showPwEmptyError() {
        binding.etPw.error = getString(R.string.pw_empty_error_msg)
    }

    fun goMovieSearch() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }


}
