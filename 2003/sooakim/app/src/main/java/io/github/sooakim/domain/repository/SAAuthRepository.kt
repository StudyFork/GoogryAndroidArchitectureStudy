package io.github.sooakim.domain.repository

import io.reactivex.Flowable

interface SAAuthRepository : SARepository {
    val isAuthRequired: Boolean

    fun login(id: String, password: String): Flowable<Unit>
}