package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Context
import android.content.Intent
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchActivity
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(context: Context, id: String, pw: String) {
        if (id != USER_ID || pw != USER_PW) {
            view.showLoginErrorToast()
        } else if (id.isEmpty()) {
            view.showIdEmptyError()
        } else if (pw.isEmpty()) {
            view.showPwEmptyError()
        } else {
            context.startActivity(Intent(context, MovieSearchActivity::class.java))
            PreferenceManager.setBoolean(context, PreferenceManager.AUTO_LOGIN_KEY, true)
            view.finishActivity()
        }
    }

    companion object {
        private const val USER_ID = "id"
        private const val USER_PW = "P@sswOrd"
    }
}