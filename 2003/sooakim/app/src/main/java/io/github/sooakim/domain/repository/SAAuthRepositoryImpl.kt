package io.github.sooakim.domain.repository

import io.github.sooakim.data.local.source.SAAuthLocalDataSource
import io.github.sooakim.data.remote.source.SAAuthRemoteDataSource
import io.reactivex.Flowable

class SAAuthRepositoryImpl(
    private val authLocalDataSource: SAAuthLocalDataSource,
    private val authRemoteDataSource: SAAuthRemoteDataSource
) : SAAuthRepository {
    override val isAuthRequired: Boolean
        get() = authLocalDataSource.isAuthRequired

    override fun login(id: String, password: String): Flowable<Unit> {
        return authRemoteDataSource.login(id, password)
            .doOnComplete { authLocalDataSource.saveAuth() }
            .toSingleDefault(Unit)
            .toFlowable()
    }
}