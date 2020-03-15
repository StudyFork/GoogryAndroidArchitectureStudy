package com.example.kangraemin.model.local.datadao

import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Single

class AuthLocalDataSourceImpl(
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