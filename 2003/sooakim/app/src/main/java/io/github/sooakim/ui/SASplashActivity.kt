package io.github.sooakim.ui

import android.content.Intent
import androidx.databinding.ViewDataBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.login.SALoginActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SASplashActivity : SAActivity<ViewDataBinding, SASplashViewModel, SASplashState>() {
    override val viewModel: SASplashViewModel by viewModel()

    override fun onState(newState: SASplashState) {
        when (newState) {
            is SASplashState.ShowLogin -> {
                startActivity(Intent(this, SALoginActivity::class.java))
                finish()
            }
            is SASplashState.ShowMain -> {
                startActivity(Intent(this, SAMovieSearchActivity::class.java))
                finish()
            }
        }
    }
}