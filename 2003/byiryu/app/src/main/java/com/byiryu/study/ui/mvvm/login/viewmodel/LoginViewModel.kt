package com.byiryu.study.ui.mvvm.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.enums.NetStatus
import com.byiryu.study.ui.mvvm.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginViewModel(private val repository: Repository) : BaseViewModel(){

    val netStatus = MutableLiveData<NetStatus>()
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val autoLogin = MutableLiveData<Boolean>()
    val loginStatus = MutableLiveData<Pair<Boolean, Int>>()

    fun loginProcess(){

        if (id.value?.isEmpty()!!) {
            loginStatus.value = Pair(false, R.string.msg_invalid_id)
            return
        }

        if (pw.value?.isEmpty()!!) {
            loginStatus.value = Pair(false, R.string.msg_invalid_pw)
            return
        }

        disposable.add( repository.loginCheck(id.value, pw.value)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                netStatus.value = NetStatus.LOADING
            }
            .doOnSuccess{
                netStatus.value = NetStatus.SUCCESS
            }
            .doOnError {
                netStatus.value = NetStatus.FAIL
                loginStatus.value = Pair(false, R.string.msg_error_loading)
            }.doAfterTerminate {
                netStatus.value = NetStatus.SUCCESS
            }
            .subscribe({
                if (it) {
                    autoLogin.value?.run {
                        repository.setAutoLogin()
                    }
                    loginStatus.value = Pair(true, -1)
                } else {
                    loginStatus.value = Pair(false, R.string.msg_incorrect_login)
                }
            }, {
                loginStatus.value = Pair(false, R.string.msg_error_loading)
            })
        )
    }
}