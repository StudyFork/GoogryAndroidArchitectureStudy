package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
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
                }

            })
            isPwEmpty.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showPwEmptyError()
                }

            })
            loginErrorMsg.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showToast(getString(R.string.id_pw_not_correct_error_msg))
                }

            })
            successLogin.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    goMovieSearch()
                }

            })
        }
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
