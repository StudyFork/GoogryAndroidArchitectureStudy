package com.mtjin.androidarchitecturestudy.ui.login

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(id: String, pw: String) {
        if (id != USER_ID || pw != USER_PW) {
            view.showLoginErrorToast()
        } else if (id.isEmpty()) {
            view.showIdEmptyError()
        } else if (pw.isEmpty()) {
            view.showPwEmptyError()
        } else {
            view.goMovieSearchActivity()
            view.saveAutoLoginSharedPref()
            view.finishActivity()
        }
    }

    companion object {
        private const val USER_ID = "id"
        private const val USER_PW = "P@sswOrd"
    }
}