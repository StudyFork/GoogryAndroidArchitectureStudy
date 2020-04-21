package com.tsdev.tsandroid.presenter


interface MovieContract {
    interface View {
        //Not yet..
    }

    interface Presenter {
        fun loadMovie(query: String)
    }
}