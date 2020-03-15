package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.model.local.datamodel.Auth
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : KangBaseActivity() {

    val authRepository by lazy {
        val db = AppDatabase.getInstance(context = this)
        AuthRepository(authLocalDataSource = AuthLocalDataSourceImpl(db.authDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val getAuth = authRepository
            .getAuth()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.autoLogin) {
                    moveMain()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(getAuth)

        val validInput = Observable
            .combineLatest(
                et_id.textChanges(),
                et_pw.textChanges(),
                BiFunction { t1: CharSequence, t2: CharSequence -> t1.isNotEmpty() && t2.isNotEmpty() }
            )
            .subscribe { loginInfoEntered ->
                btn_login.isEnabled = loginInfoEntered
                if (loginInfoEntered) {
                    btn_login.alpha = 1f
                } else {
                    btn_login.alpha = 0.5f
                }
            }
        compositeDisposable.add(validInput)

        val whenIdFocusChange = et_id.focusChanges()
            .skip(1)
            .subscribe { hasFocus ->
                if (!hasFocus) {
                    if (et_id.text.toString().isEmpty()) {
                        layout_id.error = getString(R.string.login_error_id_empty)
                    } else {
                        layout_id.error = null
                    }
                } else {
                    layout_id.error = null
                }
            }
        compositeDisposable.add(whenIdFocusChange)

        val whenPwFocusChange = et_pw.focusChanges()
            .skip(1)
            .subscribe { hasFocus ->
                if (!hasFocus) {
                    if (et_pw.text.toString().isEmpty()) {
                        layout_pw.error = getString(R.string.login_error_pw_empty)
                    } else {
                        layout_pw.error = null
                    }
                } else {
                    layout_pw.error = null
                }
            }
        compositeDisposable.add(whenPwFocusChange)

        val whenLoginButtonClicked = btn_login.clicks()
            .subscribe {
                val loginSuccess = login(et_id.text.toString(), et_pw.text.toString())
                if (loginSuccess) {
                    if (chb_auto_login.isChecked) {
                        val auth = Auth(autoLogin = true)
                        val addAuth = authRepository
                            .addAuth(auth = auth)
                            .subscribe({ // no-op

                            }, { it.printStackTrace() })
                        compositeDisposable.add(addAuth)
                    }
                    layout_pw.error = ""
                    moveMain()
                } else {
                    layout_pw.error = getString(R.string.login_fail)
                }
            }
        compositeDisposable.add(whenLoginButtonClicked)
    }

    private fun login(id: String, password: String): Boolean {
        if (id == "id" && password == "P@ssw0rd") {
            return true
        }
        return false
    }

    private fun moveMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG_AUTO_LOGIN = "auto_login"
        const val TAG_USER_INFO = "user_info"
    }
}
