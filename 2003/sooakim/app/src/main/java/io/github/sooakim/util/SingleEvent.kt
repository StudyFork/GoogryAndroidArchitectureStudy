package io.github.sooakim.util

import java.util.concurrent.atomic.AtomicBoolean

data class SingleEvent<E>(
    val event: E,
    val isConsumed: AtomicBoolean = AtomicBoolean(false)
)