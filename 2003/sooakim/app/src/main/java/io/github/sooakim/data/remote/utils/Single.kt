package io.github.sooakim.data.remote.utils

import io.reactivex.Single

inline fun <reified T : Collection<E>, reified E> Single<T>.throwIfEmpty(): Single<T> {
    return this.flatMap { item ->
        Single.defer {
            if (item.isEmpty()) {
                Single.error(IllegalStateException("query result is empty"))
            } else {
                Single.just(item)
            }
        }
    }
}