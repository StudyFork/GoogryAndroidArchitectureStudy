package com.byiryu.study.ui.login

import com.byiryu.study.ui.base.BaseContract

interface LoginContract {
    interface View : BaseContract.View

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun invalid(id: String, pw: String): Boolean

        fun login(autoLogin : Boolean)

    }
}

