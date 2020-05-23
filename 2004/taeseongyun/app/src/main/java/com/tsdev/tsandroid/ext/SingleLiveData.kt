package com.tsdev.tsandroid.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

typealias SingleEventLiveData<VALUE> = LiveData<Event<VALUE>>
typealias SingleEventMutableLiveData<VALUE> = MutableLiveData<Event<VALUE>>

open class Event<out VALUE>(private val content: VALUE) {
    var _isPending = false
        private set

    fun getValueSingleData(): VALUE? {
        return if (_isPending) {
            null
        } else {
            _isPending = true
            content
        }
    }

    fun peekContent(): VALUE = content
}

class ObserverEvent<VALUE>(private val onCheckedData: (VALUE) -> Unit) : Observer<Event<VALUE>> {
    override fun onChanged(t: Event<VALUE>?) {
        t?.getValueSingleData()?.let(onCheckedData)
    }
}

inline fun <T> SingleEventLiveData<T>.observer(
    lifecycleOwner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    observe(lifecycleOwner, ObserverEvent { observer(it) })
}

class SingleMutableLiveData<VALUE> : SingleEventMutableLiveData<VALUE>() {
    var event: VALUE?
        set(value) {
            value?.let { setValue(Event(value)) }
        }
        get() = value?.peekContent()

    fun postEvent(value: VALUE?) {
        value?.let {
            postValue(Event(it))
        }
    }
}