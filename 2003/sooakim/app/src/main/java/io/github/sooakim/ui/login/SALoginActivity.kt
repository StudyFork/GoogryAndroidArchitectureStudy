package io.github.sooakim.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding3.view.clicks
import io.github.sooakim.R
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SALoginActivity : SAActivity<SALoginPresenter>(), SALoginContractor.View {
    private lateinit var idInputEditText: TextInputEditText
    private lateinit var passwordInputEditText: TextInputEditText
    private lateinit var loginButton: AppCompatButton
    private lateinit var loadingProgressBar: ProgressBar

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val presenter: SALoginPresenter by lazy {
        SALoginPresenter(
            authRepository = requireApplication().authRepository,
            view = this
        )
    }

    override val commonProgressView: View?
        get() = loadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        bindRx()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        idInputEditText = findViewById(R.id.iet_id)
        passwordInputEditText = findViewById(R.id.iet_password)
        loginButton = findViewById(R.id.btn_login)
        loadingProgressBar = findViewById(R.id.pgb_loading)
    }

    private fun bindRx() {
        loginButton.clicks()
            .subscribe {
                presenter.doLogin(
                    id = idInputEditText.text.toString(),
                    password = passwordInputEditText.text.toString()
                )
            }
            .addTo(compositeDisposable)
    }

    override fun clearErrors() {
        idInputEditText.error = null
        passwordInputEditText.error = null
    }

    override fun showIdError() {
        idInputEditText.error = getString(R.string.activity_login_iet_id_error)
    }

    override fun showPasswordError() {
        passwordInputEditText.error = getString(R.string.activity_login_iet_password_error)
    }

    override fun showMovieSearch() {
        startActivity(Intent(this, SAMovieSearchActivity::class.java))
        finish()
    }
}