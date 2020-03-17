package com.byiryu.study.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
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

        initView()

        bind()
    }

    private fun initView(){

    }

    @SuppressLint("CheckResult")
    private fun bind() {

        btn_login.setOnClickListener {
            val id = editText_id.text.toString()
            val pw = editText_pw.text.toString()
            if (loginPresenter.invalid(id, pw)) {
                loginPresenter.login(btn_auto_login.isChecked)
            }
        }

    }

    override fun goActivity() {
        goActivity(MainActivity::class.java)
        finish()
    }


}