package com.example.kangraemin.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.ui.login.LoginActivity
import com.example.kangraemin.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : KangBaseActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

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
