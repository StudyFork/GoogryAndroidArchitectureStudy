package io.github.sooakim.data

import io.github.sooakim.data.local.SAAuthLocalDataSource
import io.github.sooakim.data.remote.SAAuthRemoteDataSource
import io.github.sooakim.domain.repository.SAAuthRepository
import io.reactivex.Flowable

internal class SAAuthRepositoryImpl(
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