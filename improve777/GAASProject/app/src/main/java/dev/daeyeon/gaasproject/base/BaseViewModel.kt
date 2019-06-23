package dev.daeyeon.gaasproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.daeyeon.gaasproject.util.Event

abstract class BaseViewModel : ViewModel() {

    protected val _completeEvent = MutableLiveData<Event<Unit>>()
    val completeEvent: LiveData<Event<Unit>>
        get() = _completeEvent

    /**
     * 프로그레스바
     */
    protected val _isShowProgressBar = MutableLiveData<Boolean>(false)
    val isShowProgressBar: LiveData<Boolean>
        get() = _isShowProgressBar

    fun complete() {
        _completeEvent.value = Event(Unit)
    }
}