package io.github.sooakim.data.local.utils

import androidx.room.EmptyResultSetException
import io.reactivex.Flowable

inline fun <reified T : Collection<E>, reified E> Flowable<T>.throwIfEmpty(): Flowable<T> {
    return this.flatMap { item ->
        Flowable.defer {
            if (item.isEmpty()) {
                Flowable.error(EmptyResultSetException("query result is empty"))
            } else {
                Flowable.just(item)
            }
        }
    }
}