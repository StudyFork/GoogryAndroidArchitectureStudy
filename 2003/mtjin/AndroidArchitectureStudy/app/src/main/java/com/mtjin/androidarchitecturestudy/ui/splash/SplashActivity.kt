package com.mtjin.androidarchitecturestudy.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.ui.login.LoginActivity
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchActivity
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class SplashActivity : AppCompatActivity(), SplashContract.View {
    private lateinit var presenter: SplashContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SplashPresenter(this)
        presenter.doSplash()
    }

    override fun showAutoLogin() {
        Toast.makeText(this, getString(R.string.auto_login_msg), Toast.LENGTH_SHORT).show()
    }

    override fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun goMovieSearch() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }

    override fun checkNetworkState(): Boolean {
        return PreferenceManager.getBoolean(this, PreferenceManager.AUTO_LOGIN_KEY)
    }
}
