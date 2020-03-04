package io.github.sooakim.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.sooakim.ui.login.SALoginActivity

class SASplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routeMovieSearch()
    }

    private fun routeMovieSearch() {
        startActivity(Intent(applicationContext, SALoginActivity::class.java))
    }
}