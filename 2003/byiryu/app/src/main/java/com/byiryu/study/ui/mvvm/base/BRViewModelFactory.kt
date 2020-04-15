package com.byiryu.study.ui.mvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.mvvm.login.viewmodel.LoginViewModel
import com.byiryu.study.ui.mvvm.main.viewmodel.MainViewModel


class BRViewModelFactory (private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(this.repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(this.repository) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}