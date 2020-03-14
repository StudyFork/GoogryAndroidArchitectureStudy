package com.example.kangraemin.model

import com.example.kangraemin.model.local.datadao.AuthDataSource
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class AuthRepository(
    val authDataSource: AuthDataSource
) {

    fun getAuth(): Flowable<Auth> {
        return authDataSource
            .getAuth()
            .subscribeOn(Schedulers.io())
            .toFlowable()
    }

    fun deleteAuth(): Completable {
        return authDataSource
            .deleteAuth()
            .subscribeOn(Schedulers.io())
    }

    fun addAuth(auth: Auth): Completable {
        return authDataSource
            .addAuth(auth = auth)
            .subscribeOn(Schedulers.io())
    }
}