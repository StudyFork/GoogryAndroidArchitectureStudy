package com.example.data.source

import com.example.data.mapper.AuthMapper
import com.example.data.model.Auth
import com.example.local.source.AuthLocalDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

internal class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override fun getAuth(): Flowable<Auth> {
        return authLocalDataSource
            .getAuth()
            .map { AuthMapper.localAuthToDataAuth(it) }
            .subscribeOn(Schedulers.io())
            .toFlowable()
    }

    override fun deleteAuth(): Completable {
        return authLocalDataSource
            .deleteAuth()
            .subscribeOn(Schedulers.io())
    }

    override fun addAuth(auth: Auth): Completable {
        return authLocalDataSource
            .addAuth(auth = AuthMapper.dataAuthToLocalAuth(auth))
            .subscribeOn(Schedulers.io())
    }
}