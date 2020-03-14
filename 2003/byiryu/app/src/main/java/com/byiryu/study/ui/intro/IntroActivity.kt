package com.byiryu.study.ui.intro

import android.os.Bundle
import android.os.Handler
import com.byiryu.study.R
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.login.LoginActivity
import com.byiryu.study.ui.main.MainActivity


class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_intro)

        initView()

    }

    private fun initView() {

        val handler = Handler()
        handler.postDelayed({
            if (getBRApplication().repository.isAutoLogin()) {
                goActivity(MainActivity::class.java)
                finish()
            } else {
                goActivity(LoginActivity::class.java)
                finish()
            }
        }, 2000)
    }


}