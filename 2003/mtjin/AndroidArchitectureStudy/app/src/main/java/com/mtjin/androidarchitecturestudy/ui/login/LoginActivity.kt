package com.mtjin.androidarchitecturestudy.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepository
import com.mtjin.androidarchitecturestudy.data.login.source.LoginRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSource
import com.mtjin.androidarchitecturestudy.data.login.source.local.LoginLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.databinding.ActivityLoginBinding
import com.mtjin.androidarchitecturestudy.ui.search.MovieSearchActivity
import com.mtjin.androidarchitecturestudy.utils.PreferenceManager

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        inject()
    }

    private fun initDataBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.login = this
    }

    private fun inject() {
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        presenter = LoginPresenter(this, loginRepository)
    }

    fun onLoginClick() {
        val id = binding.etId.text.toString().trim()
        val pw = binding.etPw.text.toString().trim()
        presenter.doLogin(id, pw)
    }


    override fun showLoginError() {
        Toast.makeText(this, getString(R.string.id_pw_not_correct_error_msg), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showIdEmptyError() {
        binding.etId.error = getString(R.string.id_empty_error_msg)
    }

    override fun showPwEmptyError() {
        binding.etPw.error = getString(R.string.pw_empty_error_msg)
    }

    override fun goMovieSearch() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }


}
