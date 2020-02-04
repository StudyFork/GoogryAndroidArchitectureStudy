package com.hansung.firstproject.extension

import androidx.lifecycle.MutableLiveData


class ListLiveData<T> : MutableLiveData<ArrayList<T>>() {
    fun add(item: T) {
        val items = value
        items!!.add(item)
        value = items
    }

    fun addAll(list: List<T>?) {
        val items = value
        items!!.addAll(list!!)
        value = items
    }

    fun clear(notify: Boolean) {
        val items = value
        items!!.clear()
        if (notify) {
            value = items
        }
    }

    fun remove(item: T) {
        val items = value
        items!!.remove(item)
        value = items
    }

    fun notifyChange() {
        val items = value
        value = items
    }
}