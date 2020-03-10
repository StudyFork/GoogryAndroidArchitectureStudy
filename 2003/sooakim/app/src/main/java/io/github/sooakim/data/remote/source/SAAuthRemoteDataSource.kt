package io.github.sooakim.data.remote.source

import io.reactivex.Completable

interface SAAuthRemoteDataSource {
    fun login(id: String, password: String): Completable
}