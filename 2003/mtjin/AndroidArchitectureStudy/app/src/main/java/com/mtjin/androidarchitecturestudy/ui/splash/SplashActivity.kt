package com.mtjin.androidarchitecturestudy.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.base.BaseActivity
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.ui.login.LoginActivity
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchActivity
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class SplashActivity : BaseActivity() {
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initViewModelCallback()
        viewModel.doSplash()
    }

    private fun inject() {
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        viewModel = SplashViewModel(loginRepository)
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goMovieSearch.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    goMovieSearch()
                }
            })
            goLogin.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    goLogin()
                }
            })
        }
    }


    fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun goMovieSearch() {
        showToast(getString(R.string.auto_login_msg))
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }
}
