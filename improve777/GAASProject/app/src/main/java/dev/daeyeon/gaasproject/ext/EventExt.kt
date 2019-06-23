package dev.daeyeon.gaasproject.ext

import dev.daeyeon.gaasproject.util.Event

fun <T> Event<T>.popContent(block: (T) -> Unit) {
    this.getContentIfNotHandled()?.let {
        block(it)
    }
}
