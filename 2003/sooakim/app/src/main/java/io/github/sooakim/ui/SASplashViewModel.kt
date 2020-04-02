package io.github.sooakim.ui

import io.github.sooakim.domain.repository.SAAuthRepository
import io.github.sooakim.ui.base.SAViewModel

class SASplashViewModel(
    authRepository: SAAuthRepository,
    navigator: SASplashNavigator
) : SAViewModel<SASplashNavigator>(navigator) {

    init {
        val isAuthRequired = authRepository.isAuthRequired
        if (isAuthRequired) {
            navigator.showLogin()
        } else {
            navigator.showMain()
        }
    }
}