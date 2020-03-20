package com.byiryu.study.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View{


    private val loginPresenter by lazy {
        LoginPresenter<LoginContract.View>(getBRApplication().repository)
    }
    override val progressBar: View?
        get() = loading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter.onAttach(this)

        bind()
    }

    @SuppressLint("CheckResult")
    private fun bind() {

        btn_login.setOnClickListener {
            val id = editText_id.text.toString()
            val pw = editText_pw.text.toString()
            loginPresenter.login(id, pw, btn_auto_login.isChecked)
        }

    }

    override fun goActivityMain() {
        goActivity(MainActivity::class.java)
        finish()
    }

    override fun onDestroy() {
        loginPresenter.onDetach()
        super.onDestroy()

    }
}