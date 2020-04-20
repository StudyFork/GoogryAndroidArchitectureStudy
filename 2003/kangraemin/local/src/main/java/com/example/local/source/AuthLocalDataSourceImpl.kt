package com.example.local.source

import com.example.local.model.Auth
import io.reactivex.Completable
import io.reactivex.Single

internal class AuthLocalDataSourceImpl(
    private val authDao: AuthDao
) : AuthLocalDataSource {
    override fun getAuth(): Single<Auth> {
        return authDao
            .getAuth()
    }

    override fun addAuth(auth: Auth): Completable {
        return authDao
            .insertAuth(auth = auth)
    }

    override fun deleteAuth(): Completable {
        return authDao
            .deleteAuth()
    }
}