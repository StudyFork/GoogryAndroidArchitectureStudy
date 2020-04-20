package com.example.data.source

import com.example.data.mapper.AuthMapper
import com.example.data.model.Auth
import com.example.local.source.AuthLocalDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class AuthRepository(
    private val authLocalDataSource: AuthLocalDataSource
) {

    fun getAuth(): Flowable<Auth> {
        return authLocalDataSource
            .getAuth()
            .map { AuthMapper.localAuthToDataAuth(it) }
            .subscribeOn(Schedulers.io())
            .toFlowable()
    }

    fun deleteAuth(): Completable {
        return authLocalDataSource
            .deleteAuth()
            .subscribeOn(Schedulers.io())
    }

    fun addAuth(auth: Auth): Completable {
        return authLocalDataSource
            .addAuth(auth = AuthMapper.dataAuthToLocalAuth(auth))
            .subscribeOn(Schedulers.io())
    }
}