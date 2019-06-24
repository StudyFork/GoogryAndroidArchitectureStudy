package com.namjackson.archstudy.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namjackson.archstudy.util.Event

abstract class BaseViewModel : ViewModel() {

    protected val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>>
        get() = _showToastEvent

    protected var _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading
}