package dev.daeyeon.gaasproject.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.daeyeon.gaasproject.util.Event

abstract class BaseViewModel : ViewModel() {

    private val _completeEvent = MutableLiveData<Event<Any>>()
    val completeEvent get() = _completeEvent

    fun complete() {
        _completeEvent.value = Event(Any())
    }
}