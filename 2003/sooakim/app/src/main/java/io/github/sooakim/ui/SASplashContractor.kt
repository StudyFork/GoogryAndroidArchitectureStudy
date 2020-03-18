package io.github.sooakim.ui

import io.github.sooakim.ui.base.SABasePresenter
import io.github.sooakim.ui.base.SABaseView

interface SASplashContractor {
    interface View : SABaseView {
        fun showLogin()
        fun showMain()
    }

    interface Presenter : SABasePresenter {

    }
}