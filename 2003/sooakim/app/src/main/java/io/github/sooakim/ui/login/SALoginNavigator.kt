package io.github.sooakim.ui.login

import io.github.sooakim.ui.base.SANavigationProvider
import io.github.sooakim.ui.base.SANavigator
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SALoginNavigator(
    navigator: SANavigationProvider
) : SANavigator(navigator) {
    fun showMain() {
        navigator.startActivity(SAMovieSearchActivity::class)
        navigator.finishActivity()
    }
}