package io.github.sooakim.ui

import androidx.databinding.ViewDataBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.base.SANavigationProviderImpl

class SASplashActivity : SAActivity<ViewDataBinding, SASplashViewModel>() {
    override val viewModel: SASplashViewModel by lazy {
        SASplashViewModel(
            authRepository = requireApplication().authRepository,
            navigator = SASplashNavigator(
                SANavigationProviderImpl(this)
            )
        )
    }
}