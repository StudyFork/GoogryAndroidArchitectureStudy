package com.example.kangraemin.ui.splash

import com.example.kangraemin.base.KangBasePresenter

interface SplashContract {
    interface View {
        fun startLoginActivity()
    }

    interface Presenter: KangBasePresenter {
        fun startTimer()
    }
}