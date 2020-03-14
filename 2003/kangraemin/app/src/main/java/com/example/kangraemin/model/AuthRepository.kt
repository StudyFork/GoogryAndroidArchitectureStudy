package com.example.kangraemin.model

import com.example.kangraemin.model.local.datadao.AuthLocalDataSource
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class AuthRepository(
    val authLocalDataSource: AuthLocalDataSource
) {

    fun getAuth(): Flowable<Auth> {
        return authLocalDataSource
            .getAuth()
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
            .addAuth(auth = auth)
            .subscribeOn(Schedulers.io())
    }
}