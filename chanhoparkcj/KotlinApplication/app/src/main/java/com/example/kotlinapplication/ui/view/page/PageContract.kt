package com.example.kotlinapplication.ui.view.page

interface PageContract {
    interface Presenter {
        fun loadData(query: String)
    }

    interface View<T> {
        fun getItems(items: List<T>)
        fun getError(message: String)
    }
}