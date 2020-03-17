package com.example.kangraemin.ui.login

import android.content.Intent
import android.os.Bundle
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.ui.main.MainActivity
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : KangBaseActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    private val authRepository by lazy {
        val db = AppDatabase.getInstance(context = this)
        AuthRepository(authLocalDataSource = AuthLocalDataSourceImpl(db.authDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, authRepository = authRepository)

        val checkLoginInfoHasEntered = Observable
            .combineLatest(
                et_id.textChanges(),
                et_pw.textChanges(),
                BiFunction { id: CharSequence, password: CharSequence ->
                    presenter
                        .checkLoginInfoHasEntered(
                            id = id.toString(),
                            password = password.toString()
                        )
                }
            )
            .subscribe { loginInfoHasEntered ->
                presenter.activateButton(loginInfoHasEntered)
            }
        compositeDisposable.add(checkLoginInfoHasEntered)

        val whenIdFocusChange = et_id.focusChanges()
            .skip(1)
            .subscribe { hasFocus ->
                presenter.checkIdIsEmpty(id = et_id.text.toString(), hasFocus = hasFocus)
            }
        compositeDisposable.add(whenIdFocusChange)

        val whenPwFocusChange = et_pw.focusChanges()
            .skip(1)
            .subscribe { hasFocus ->
                presenter.checkPasswordIsEmpty(
                    password = et_pw.text.toString(),
                    hasFocus = hasFocus
                )
            }
        compositeDisposable.add(whenPwFocusChange)

        val whenLoginButtonClicked = btn_login.clicks()
            .subscribe {
                presenter.login(
                    id = et_id.text.toString(),
                    password = et_pw.text.toString(),
                    isAutoLogin = chb_auto_login.isChecked
                )
            }
        compositeDisposable.add(whenLoginButtonClicked)
    }

    override fun showEmptyIdError() {
        layout_id.error = getString(R.string.login_error_id_empty)
    }

    override fun hideEmptyIdError() {
        layout_id.error = null
    }

    override fun showPasswordEmptyError() {
        layout_pw.error = getString(R.string.login_error_pw_empty)
    }

    override fun hidePasswordEmptyError() {
        layout_pw.error = null
    }

    override fun showFailedLoginError() {
        layout_pw.error = getString(R.string.login_fail)
    }

    override fun hideFailedLoginError() {
        layout_pw.error = null
    }

    override fun enableLoginButton() {
        btn_login.isEnabled = true
        btn_login.alpha = 1f
    }

    override fun disableLoginButton() {
        btn_login.isEnabled = false
        btn_login.alpha = 0.5f
    }

    override fun startMainActivity() {
        layout_pw.error = ""
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
