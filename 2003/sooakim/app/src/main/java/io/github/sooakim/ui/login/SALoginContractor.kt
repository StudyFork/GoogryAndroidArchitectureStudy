package io.github.sooakim.ui.login

import io.github.sooakim.ui.base.SABasePresenter
import io.github.sooakim.ui.base.SABaseView

interface SALoginContractor {
    interface View : SABaseView {
        fun clearErrors()
        fun showIdError()
        fun showPasswordError()

        fun showMovieSearch()
    }

    interface Presenter : SABasePresenter {
        fun login(id: String, password: String)
    }
}