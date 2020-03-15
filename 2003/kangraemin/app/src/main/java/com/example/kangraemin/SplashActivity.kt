package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.contract.SplashContract
import com.example.kangraemin.presenter.SplashPresenter

class SplashActivity : KangBaseActivity(), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this)

        presenter.startTimer()
    }

    override fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        presenter.disposeCompositDisposable()
        super.onDestroy()
    }
}
