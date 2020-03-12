package com.byiryu.study.ui.intro

import android.os.Bundle
import android.os.Handler
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.main.MainActivity


class IntroActivity : BaseActivity() {

    lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        init()

        val handler = Handler()
        handler.postDelayed({
            if (repository.isAutoLogin()) {
                goActivity(MainActivity::class.java)
                finish()
            } else {
                goActivity(LoginActivity::class.java)
                finish()
            }
        }, 2000)
    }

    private fun init() {
        repository = Repository(this@IntroActivity)
    }

}