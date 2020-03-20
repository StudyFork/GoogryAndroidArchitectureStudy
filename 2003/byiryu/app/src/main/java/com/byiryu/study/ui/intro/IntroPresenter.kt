package com.byiryu.study.ui.intro

import android.os.Handler
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.base.BasePresenter

class IntroPresenter<V : IntroContract.View> constructor(
    private val repository: Repository
) : BasePresenter<V>(), IntroContract.Presenter<V> {

    override fun onAttach(view: V) {
        super.onAttach(view)
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