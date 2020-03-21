package com.byiryu.study.ui.intro

import android.os.Bundle
import com.byiryu.study.R
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.base.BaseContract
import com.byiryu.study.ui.base.BasePresenter
import com.byiryu.study.ui.login.LoginActivity
import com.byiryu.study.ui.main.MainActivity


class IntroActivity : BaseActivity(), IntroContract.View{

    @Suppress("UNCHECKED_CAST")
    override val presenter: BaseContract.Presenter<BaseContract.View>
        get() = introPresenter as BasePresenter<BaseContract.View>

    private val introPresenter by lazy {
        IntroPresenter<IntroContract.View>(getBRApplication().repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    override fun goActivityMain() {
        goActivity(MainActivity::class.java)
        finish()
    }

    override fun goActivityLogin() {
        goActivity(LoginActivity::class.java)
        finish()
    }


}