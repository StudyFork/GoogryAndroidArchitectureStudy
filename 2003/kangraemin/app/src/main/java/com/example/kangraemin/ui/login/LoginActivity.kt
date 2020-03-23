package com.example.kangraemin.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kangraemin.R
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.databinding.ActivityLoginBinding
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.ui.main.MainActivity
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class LoginActivity : KangBaseActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        presenter = LoginPresenter(
            this,
            authRepository = AuthRepository(
                authLocalDataSource = AuthLocalDataSourceImpl(
                    AppDatabase.getInstance(context = this).authDao()
                )
            )
        )

        val whenTextChanged = Observable
            .combineLatest(
                binding.etId.textChanges(),
                binding.etPw.textChanges(),
                BiFunction<CharSequence, CharSequence, Pair<String, String>> { id, pw ->
                    id.toString() to pw.toString()
                }
            )
            .subscribe {
                presenter.checkLoginInfoHasEntered(it.first, it.second)
            }
        compositeDisposable.add(whenTextChanged)

        val whenIdFocusChange = binding.etId.focusChanges()
            .skip(1)
            .subscribe { hasFocus ->
                presenter.checkIdIsEmpty(id = binding.etId.text.toString(), hasFocus = hasFocus)
            }
        compositeDisposable.add(whenIdFocusChange)

        val whenPwFocusChange = binding.etPw.focusChanges()
            .skip(1)
            .subscribe { hasFocus ->
                presenter.checkPasswordIsEmpty(
                    password = binding.etPw.text.toString(),
                    hasFocus = hasFocus
                )
            }
        compositeDisposable.add(whenPwFocusChange)

        val whenLoginButtonClicked = binding.btnLogin.clicks()
            .subscribe {
                presenter.login(
                    id = binding.etId.text.toString(),
                    password = binding.etPw.text.toString(),
                    isAutoLogin = binding.chbAutoLogin.isChecked
                )
            }
        compositeDisposable.add(whenLoginButtonClicked)
    }

    override fun showEmptyIdError() {
        binding.layoutId.error = getString(R.string.login_error_id_empty)
    }

    override fun hideEmptyIdError() {
        binding.layoutId.error = null
    }

    override fun showPasswordEmptyError() {
        binding.layoutPw.error = getString(R.string.login_error_pw_empty)
    }

    override fun hidePasswordEmptyError() {
        binding.layoutPw.error = null
    }

    override fun showFailedLoginError() {
        binding.layoutPw.error = getString(R.string.login_fail)
    }

    override fun hideFailedLoginError() {
        binding.layoutPw.error = null
    }

    override fun showAddAuthError() {
        toast(getString(R.string.login_error_add_auth_toast_message))
    }

    override fun enableLoginButton() {
        binding.btnLogin.isEnabled = true
        binding.btnLogin.alpha = 1f
    }

    override fun disableLoginButton() {
        binding.btnLogin.isEnabled = false
        binding.btnLogin.alpha = 0.5f
    }

    override fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
