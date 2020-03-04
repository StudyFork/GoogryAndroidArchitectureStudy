package io.github.sooakim.ui.base

interface SAIdentifiable {
    val identifier: Any

    override operator fun equals(other: Any?): Boolean
}