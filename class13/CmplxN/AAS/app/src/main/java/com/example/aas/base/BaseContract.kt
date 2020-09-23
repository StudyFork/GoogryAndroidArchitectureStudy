package com.example.aas.base

interface BaseContract {
    interface View {

    }

    interface Presenter {
        fun onDestroy()
    }
}