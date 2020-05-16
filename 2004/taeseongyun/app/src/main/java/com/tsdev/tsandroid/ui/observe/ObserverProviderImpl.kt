package com.tsdev.tsandroid.ui.observe

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.tsdev.tsandroid.data.Item
import java.lang.Exception

const val NULL_DATA = "N/A"

class ObserverProviderImpl : ObserverProvider {

    private val movieObserverItem = ObservableArrayList<Item>()

    private val observableQuery = ObservableField<String>()

    override fun emitData() = observableQuery.get() ?: NULL_DATA

    override fun emitList() = movieObserverItem

    override fun observeList(value: Item) {
        movieObserverItem.add(value)
    }


    override fun observe(value: String) {
        try {
            observableQuery.set(value)
        } catch (e: Exception) {
        }
    }

    override fun disconnectObserve() {
        movieObserverItem.clear()
    }
}