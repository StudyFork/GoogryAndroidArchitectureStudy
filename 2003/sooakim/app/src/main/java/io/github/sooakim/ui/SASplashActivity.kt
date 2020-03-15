package io.github.sooakim.ui

import android.content.Intent
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.login.SALoginActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SASplashActivity : SAActivity<SASplashPresenter>(), SASplashContractor.View {
    override val presenter: SASplashPresenter
        get() = SASplashPresenter(
            authRepository = requireApplication().authRepository,
            view = this
        )

    override fun showLogin() {
        startActivity(Intent(this, SALoginActivity::class.java))
    }

    override fun showMain() {
        startActivity(Intent(this, SAMovieSearchActivity::class.java))
    }
}