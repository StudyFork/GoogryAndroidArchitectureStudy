package io.github.sooakim.util

data class SingleEvent<E>(
    val event: E,
    var isConsumed: Boolean = false
)