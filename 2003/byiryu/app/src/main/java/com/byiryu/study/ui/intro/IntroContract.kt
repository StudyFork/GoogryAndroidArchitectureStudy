package com.byiryu.study.ui.intro

import com.byiryu.study.ui.base.BaseContract
import com.byiryu.study.ui.base.BasePresenter

interface IntroContract {
    interface View : BaseContract.View{
        fun goActivityMain()

        fun goActivityLogin()
    }

    interface Presenter<V : View> : BaseContract.Presenter<V>{

        fun onViewPrepared()

    }
}