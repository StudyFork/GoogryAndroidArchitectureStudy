package io.github.sooakim.ui

import io.github.sooakim.ui.base.SANavigationProvider
import io.github.sooakim.ui.base.SANavigator
import io.github.sooakim.ui.login.SALoginActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SASplashNavigator(
    navigator: SANavigationProvider
) : SANavigator(navigator) {
    fun showLogin() {
        navigator.startActivity(SALoginActivity::class)
        navigator.finishActivity()
    }

    fun showMain() {
        navigator.startActivity(SAMovieSearchActivity::class)
        navigator.finishActivity()
    }
}