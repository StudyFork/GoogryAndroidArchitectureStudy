package com.example.kotlinapplication.ui.view.page

interface PageContract {
    interface Presenter<T> {
        fun loadData(query: String)
        fun setLocalData(items:List<T>)
        fun getLocalItems():List<T>?
    }

    interface View<T> {
        fun getItems(items: List<T>)
        fun getError(message: String)
    }
}