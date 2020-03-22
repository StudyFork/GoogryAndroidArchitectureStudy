package io.github.sooakim.ui

import android.content.Intent
import androidx.databinding.ViewDataBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.login.SALoginActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SASplashActivity : SAActivity<ViewDataBinding, SASplashPresenter>(), SASplashContractor.View {
    override val presenter: SASplashPresenter by lazy {
        SASplashPresenter(
            authRepository = requireApplication().authRepository,
            view = this
        )
    }

    override fun showLogin() {
        startActivity(Intent(this, SALoginActivity::class.java))
    }

    override fun showMain() {
        startActivity(Intent(this, SAMovieSearchActivity::class.java))
    }
}