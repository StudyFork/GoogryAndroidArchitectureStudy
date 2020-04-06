package com.tsdev.tsandroid.base

interface RecyclerViewModel {
    fun addItem(item: Any)

    fun addItems(items: List<Any>)

    fun clear()
}