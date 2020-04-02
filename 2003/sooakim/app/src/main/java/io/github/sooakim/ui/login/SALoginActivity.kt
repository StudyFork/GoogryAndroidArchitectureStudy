package io.github.sooakim.ui.login

import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityLoginBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.base.SANavigationProviderImpl

class SALoginActivity :
    SAActivity<ActivityLoginBinding, SALoginViewModel>(R.layout.activity_login) {
    override val viewModel: SALoginViewModel by lazy {
        SALoginViewModel(
            resourceProvider = requireApplication().resourceProvider,
            authRepository = requireApplication().authRepository,
            navigator = SALoginNavigator(
                SANavigationProviderImpl(this)
            )
        )
    }
}