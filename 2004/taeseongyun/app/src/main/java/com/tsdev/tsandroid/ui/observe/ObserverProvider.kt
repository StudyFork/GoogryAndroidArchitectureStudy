package com.tsdev.tsandroid.ui.observe

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.tsdev.tsandroid.data.Item

interface ObserverProvider {
    fun emitData(): String

    fun emitList(): ObservableArrayList<Item>

    fun observeList(value: Item)

    fun observe(value: String)

    fun disconnectObserve()
}