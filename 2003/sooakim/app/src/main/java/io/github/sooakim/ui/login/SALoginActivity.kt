package io.github.sooakim.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityLoginBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SALoginActivity : SAActivity<ActivityLoginBinding, SALoginPresenter>(),
    SALoginContractor.View {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val presenter: SALoginPresenter by lazy {
        SALoginPresenter(
            authRepository = requireApplication().authRepository,
            view = this
        )
    }

    override val layoutResId: Int
        get() = R.layout.activity_login

    override val commonProgressView: View?
        get() = viewDataBinding.pgbLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindRx()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun bindRx() {
        viewDataBinding.btnLogin.clicks()
            .subscribe {
                presenter.login(
                    id = viewDataBinding.ietId.text.toString(),
                    password = viewDataBinding.ietPassword.text.toString()
                )
            }
            .addTo(compositeDisposable)
    }

    override fun clearErrors() {
        viewDataBinding.ietId.error = null
        viewDataBinding.ietPassword.error = null
    }

    override fun showIdError() {
        viewDataBinding.ietId.error = getString(R.string.activity_login_iet_id_error)
    }

    override fun showPasswordError() {
        viewDataBinding.ietPassword.error = getString(R.string.activity_login_iet_password_error)
    }

    override fun showMovieSearch() {
        startActivity(Intent(this, SAMovieSearchActivity::class.java))
        finish()
    }
}