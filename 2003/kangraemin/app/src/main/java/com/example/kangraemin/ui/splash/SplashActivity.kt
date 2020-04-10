package com.example.kangraemin.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.ui.login.LoginActivity
import com.example.kangraemin.ui.main.MainActivity

class SplashActivity : KangBaseActivity() {

    private val authRepository by lazy {
        val db = AppDatabase.getInstance(context = this)
        AuthRepository(authLocalDataSource = AuthLocalDataSourceImpl(db.authDao()))
    }

    private val splashViewModel: SplashViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SplashViewModel(authRepository = authRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.toMain.observe(this, Observer {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        splashViewModel.needLogin.observe(this, Observer {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        })

        splashViewModel.getAuthError.observe(this, Observer {
            toast(getString(R.string.error_get_auth_toast_message))
        })
    }
}
