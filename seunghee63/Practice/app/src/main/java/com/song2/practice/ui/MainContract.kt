package com.song2.practice.ui

interface MainContract{
    interface View{
        fun showSignupSuccess()
        fun showErrorEmailEmpty()
        fun showErrorPasswordEmpty()
        fun showErrorNotExist()
    }

    interface Presenter{
        fun signin(email: String, password: String)
    }
}