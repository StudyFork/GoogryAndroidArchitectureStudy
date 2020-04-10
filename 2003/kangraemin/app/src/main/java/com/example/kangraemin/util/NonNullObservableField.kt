package com.example.kangraemin.util

import androidx.lifecycle.MutableLiveData

// https://medium.com/meesho-tech/non-null-observablefield-in-kotlin-bd72d31ab54f
class NonNullMutableLiveData<T : Any>(
    value: T
) : MutableLiveData<T>(value) {
    override fun getValue(): T {
        return super.getValue()!!
    }
}