package com.example.myproject.ui.base

import com.example.myproject.data.repository.NaverRepository

open class BasePresenter(
    private val view: BaseContract.View,
    private val repository: NaverRepository
) : BaseContract.Presenter {

}
