package com.example.architecturestudy.ui

import androidx.fragment.app.Fragment

interface MainContract {

    interface View {
        fun showFragment(fragment: Fragment)
    }

    interface Presenter {
        fun start()
    }
}