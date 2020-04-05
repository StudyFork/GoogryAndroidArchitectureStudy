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

class SplashActivity : KangBaseActivity() {

    private val authRepository by lazy {
        val db = AppDatabase.getInstance(context = this)
        AuthRepository(authLocalDataSource = AuthLocalDataSourceImpl(db.authDao()))
    }

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = SplashViewModel(authRepository = authRepository)

        splashViewModel.toMain.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        })

        splashViewModel.getAuthError.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                toast(getString(R.string.error_get_auth_toast_message))
            }

        })
        splashViewModel.needLogin.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        })
    }

    override fun onDestroy() {
        splashViewModel.onDestroy()
        super.onDestroy()
    }
}
