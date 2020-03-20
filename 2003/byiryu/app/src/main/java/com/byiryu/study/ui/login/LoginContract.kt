package com.byiryu.study.ui.login

import com.byiryu.study.ui.base.BaseContract

interface LoginContract {
    interface View : BaseContract.View{
        fun goActivityMain()
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {

        fun login(id: String, pw: String, autoLogin: Boolean)

    }
}

