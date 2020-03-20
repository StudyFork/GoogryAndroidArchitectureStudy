package com.byiryu.study.ui.base

import android.content.Intent
import androidx.annotation.StringRes

interface BaseContract {

    interface View {
        fun showMsg(@StringRes res: Int)

        fun showMsg(msg: String)

        fun showLoading()

        fun hideLoading()

        fun goActivity(clazz: Class<*>)

        fun goActivity(intent: Intent)
    }

    interface Presenter<T : View> {
        fun onAttach(view: T)

        fun onDetach()
    }
}