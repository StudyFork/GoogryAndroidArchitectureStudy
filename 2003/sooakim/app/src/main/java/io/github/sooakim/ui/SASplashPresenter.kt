package io.github.sooakim.ui

import io.github.sooakim.domain.repository.SAAuthRepository
import java.lang.ref.WeakReference

class SASplashPresenter(
    private val authRepository: SAAuthRepository,
    view: SASplashContractor.View
) : SASplashContractor.Presenter {
    private val viewRef: WeakReference<SASplashContractor.View> = WeakReference(view)

    override fun create() {
        val isAuthRequired = authRepository.isAuthRequired
        if (isAuthRequired) {
            viewRef.get()?.showLogin()
        } else {
            viewRef.get()?.showMain()
        }
    }
}