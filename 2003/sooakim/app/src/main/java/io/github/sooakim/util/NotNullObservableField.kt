package io.github.sooakim.util

import androidx.databinding.ObservableField


class NotNullObservableField<E : Any>(
    defaultValue: E
) : ObservableField<E>(defaultValue) {
    override fun get(): E {
        return super.get()!!
    }

    override fun set(value: E) {
        super.set(value)
    }
}