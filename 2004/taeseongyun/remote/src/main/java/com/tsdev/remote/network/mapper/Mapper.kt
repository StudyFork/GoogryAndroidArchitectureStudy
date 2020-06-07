package com.tsdev.remote.network.mapper

import io.reactivex.rxjava3.core.Single

interface Mapper<T, U> {
    fun toData(data: Single<T>): Single<U>

    fun fromData(data: Single<U>): Single<T>
}