package com.song2.practice.ui

import com.song2.practice.AccountRepository

class MainPresenter(
    private val view : MainContract.View,
    private val accountRepository: AccountRepository
) : MainContract.Presenter {
    override fun signin(email: String, password: String) {
        if(email.isEmpty()){
            view.showErrorEmailEmpty()
        }

        accountRepository.signin(email, password, success={}, failed)
    }


}