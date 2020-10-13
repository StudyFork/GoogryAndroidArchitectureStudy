package com.hong.architecturestudy.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.architecturestudy.ui.main.Message

open class BaseViewModel : ViewModel() {

    protected val _msg = MutableLiveData<Message>()
    val msg: LiveData<Message> get() = _msg

}