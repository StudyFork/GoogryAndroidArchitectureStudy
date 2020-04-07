package io.github.sooakim.ui.login

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityLoginBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity

class SALoginActivity : SAActivity<ActivityLoginBinding, SALoginViewModel, SALoginState>(
    layoutResId = R.layout.activity_login
) {
    override val viewModel: SALoginViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SALoginViewModel(
                    resourceProvider = requireApplication().resourceProvider,
                    authRepository = requireApplication().authRepository
                ) as T
            }
        }
    }

    override fun onState(newState: SALoginState) {
        when (newState) {
            is SALoginState.ShowMain -> {
                startActivity(Intent(this, SAMovieSearchActivity::class.java))
                finish()
            }
        }
    }
}