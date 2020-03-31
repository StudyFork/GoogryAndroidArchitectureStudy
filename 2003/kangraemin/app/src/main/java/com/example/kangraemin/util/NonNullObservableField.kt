package com.example.kangraemin.util

import androidx.databinding.Observable
import androidx.databinding.ObservableField

// https://medium.com/meesho-tech/non-null-observablefield-in-kotlin-bd72d31ab54f
class NonNullObservableField<T : Any>(
    value: T, vararg dependencies: Observable
) : ObservableField<T>(*dependencies) {
    init {
        set(value)
    }

    override fun get(): T = super.get()!!

    @Suppress("RedundantOverride")
    override fun set(value: T) = super.set(value)
}