package io.github.sooakim.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.sooakim.ui.application.SAApplication
import io.github.sooakim.ui.login.SALoginActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SASplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        route()
    }

    private fun route() {
        val isAuthRequired = (applicationContext as? SAApplication)?.preferencesHelper
            ?.isAuthRequired
            ?: return
        if (isAuthRequired) {
            routeLogin()
        } else {
            routeMovieSearch()
        }
    }

    private fun routeLogin() {
        startActivity(Intent(applicationContext, SALoginActivity::class.java))
    }

    private fun routeMovieSearch() {
        startActivity(Intent(applicationContext, SAMovieSearchActivity::class.java))
    }
}