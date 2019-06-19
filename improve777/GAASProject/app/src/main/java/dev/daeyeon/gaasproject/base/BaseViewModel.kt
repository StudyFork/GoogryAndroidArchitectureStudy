package dev.daeyeon.gaasproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.daeyeon.gaasproject.util.Event

abstract class BaseViewModel : ViewModel() {

    private val _completeEvent = MutableLiveData<Event<Unit>>()
    val completeEvent: LiveData<Event<Unit>>
        get() = _completeEvent

    fun complete() {
        _completeEvent.value = Event(Unit)
    }
}