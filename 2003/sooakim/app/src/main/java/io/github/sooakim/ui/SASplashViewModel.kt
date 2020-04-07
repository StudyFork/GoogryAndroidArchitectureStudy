package io.github.sooakim.ui

import io.github.sooakim.domain.repository.SAAuthRepository
import io.github.sooakim.ui.base.SAViewModel

class SASplashViewModel(
    authRepository: SAAuthRepository
) : SAViewModel<SASplashState>() {

    init {
        val isAuthRequired = authRepository.isAuthRequired
        if (isAuthRequired) {
            runState(SASplashState.ShowLogin)
        } else {
            runState(SASplashState.ShowMain)
        }
    }
}