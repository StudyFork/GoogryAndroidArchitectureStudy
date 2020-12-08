package com.hhi.myapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val visible = MutableLiveData<Boolean>()
}