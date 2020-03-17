package com.byiryu.study.ui.intro

import android.content.Intent
import android.os.Handler
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.base.BasePresenter
import com.byiryu.study.ui.login.LoginActivity
import com.byiryu.study.ui.main.MainActivity

class IntroPresenter<V : IntroContract.View> constructor(private val repository: Repository) : BasePresenter<V>(), IntroContract.Presenter<V>  {

    override fun onViewPrepared() {
        val handler = Handler()
        handler.postDelayed({
            if (repository.isAutoLogin()) {
                mvpView?.goActivityMain()
            } else {
                mvpView?.goActivityLogin()
            }
        }, 2000)
    }

}