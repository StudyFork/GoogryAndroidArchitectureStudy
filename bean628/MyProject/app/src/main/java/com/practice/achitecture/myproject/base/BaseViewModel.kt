package com.practice.achitecture.myproject.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val eventProgressBarIsShowing = MutableLiveData<Boolean>(false)
    var eventHideSoftKeyboard = MutableLiveData<Boolean>(false)
    var eventStringMessageId = MutableLiveData<Int>(-999)

}