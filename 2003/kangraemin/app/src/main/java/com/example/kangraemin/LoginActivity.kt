package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import com.example.kangraemin.base.KangBaseActivity
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : KangBaseActivity() {

    var hasEnteredId = false
    var hasEnteredPw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("user_info", 0)
        val editor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean("auto_login", false)) {
            moveMain()
        }

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
            .subscribe { hasFocus ->
                if (!hasFocus) {
                    if (et_id.text.toString().isEmpty() && hasEnteredId) {
                        layout_id.error = getString(R.string.login_error_id_empty)
                    } else {
                        hasEnteredId = true
                        layout_id.error = null
                    }
                } else {
                    layout_id.error = null
                }
            }
        compositeDisposable.add(whenIdFocusChange)

        val whenPwFocusChange = et_pw.focusChanges()
            .subscribe { hasFocus ->
                if (!hasFocus) {
                    if (et_pw.text.toString().isEmpty() && hasEnteredPw) {
                        layout_pw.error = getString(R.string.login_error_pw_empty)
                    } else {
                        hasEnteredPw = true
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
                        editor.putBoolean("auto_login", true)
                        editor.apply()
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
}
