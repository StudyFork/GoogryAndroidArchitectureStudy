package com.example.dkarch.presentation.base

interface BaseContract {
    interface View {

    }

    interface Presenter {
        val view: View

        fun onDestroy()
    }
}
