package io.github.sooakim.ui

import android.content.Intent
import android.os.Bundle
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.login.SALoginActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SASplashActivity : SAActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        route()
    }

    private fun route() {
        if (requireApplication().authRepository.isAuthRequired) {
            routeLogin()
        } else {
            routeMovieSearch()
        }
    }

    private fun routeLogin() {
        startActivity(Intent(this, SALoginActivity::class.java))
    }

    private fun routeMovieSearch() {
        startActivity(Intent(this, SAMovieSearchActivity::class.java))
    }
}