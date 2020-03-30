package com.example.kangraemin.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.ui.login.LoginActivity
import com.example.kangraemin.ui.main.MainActivity
import com.example.kangraemin.ui.splash.SplashViewModel.SplashResult.*

class SplashActivity : KangBaseActivity() {

    private val authRepository by lazy {
        val db = AppDatabase.getInstance(context = this)
        AuthRepository(authLocalDataSource = AuthLocalDataSourceImpl(db.authDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashViewModel = SplashViewModel(authRepository = authRepository)
        splashViewModel.observableSplashResult.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (splashViewModel.observableSplashResult.get()) {
                    MAIN_VIEW -> {
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    NEED_LOGIN -> {
                        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    AUTH_ERROR -> {
                        toast(getString(R.string.error_get_auth_toast_message))
                    }
                }
            }
        })

    }

    override fun onDestroy() {
//        presenter.onViewDestroy()
        super.onDestroy()
    }
}
