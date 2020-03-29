package io.github.sooakim.ui.movie

import android.content.Intent
import androidx.core.net.toUri
import io.github.sooakim.ui.base.SANavigationProvider
import io.github.sooakim.ui.base.SANavigator

class SAMovieSearchNavigator(
    navigator: SANavigationProvider
) : SANavigator(navigator) {
    fun showMovieLink(url: String) {
        navigator.startActivity(Intent.ACTION_VIEW, url.toUri())
    }
}