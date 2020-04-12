package io.github.sooakim.ui.login

import android.content.Intent
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityLoginBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SALoginActivity : SAActivity<ActivityLoginBinding, SALoginViewModel, SALoginState>(
    layoutResId = R.layout.activity_login
) {
    override val viewModel: SALoginViewModel by viewModel()

    override fun onState(newState: SALoginState) {
        when (newState) {
            is SALoginState.ShowMain -> {
                startActivity(Intent(this, SAMovieSearchActivity::class.java))
                finish()
            }
        }
    }
}