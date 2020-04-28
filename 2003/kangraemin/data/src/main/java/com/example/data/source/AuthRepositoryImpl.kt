package com.example.data.source

import com.example.data.model.Auth
import com.example.data.source.local.AuthLocalDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

internal class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override fun getAuth(): Flowable<Auth> {
        return authLocalDataSource
            .getAuth()
            .toFlowable()
    }

    override fun deleteAuth(): Completable {
        return authLocalDataSource
            .deleteAuth()
    }

    override fun addAuth(auth: Auth): Completable {
        return authLocalDataSource
            .addAuth(auth = auth)
    }
}