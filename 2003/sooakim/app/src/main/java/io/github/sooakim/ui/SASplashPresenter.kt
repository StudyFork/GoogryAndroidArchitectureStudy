package io.github.sooakim.ui

import io.github.sooakim.domain.repository.SAAuthRepository

class SASplashPresenter(
    private val authRepository: SAAuthRepository,
    private val view: SASplashContractor.View
) : SASplashContractor.Presenter {

    init {
        val isAuthRequired = authRepository.isAuthRequired
        if (isAuthRequired) {
            view.showLogin()
        } else {
            view.showMain()
        }
    }
}