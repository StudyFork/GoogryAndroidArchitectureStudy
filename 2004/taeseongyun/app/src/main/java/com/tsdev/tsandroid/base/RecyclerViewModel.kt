package com.tsdev.tsandroid.base

interface RecyclerViewModel<ITEM> {
    fun  addItem(item: ITEM)

    fun addItems(items: List<ITEM>)

    fun clear()
}