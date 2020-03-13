package com.example.kangraemin.model.local.datadao

import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Single

class AuthImpl(
    private val db: AppDatabase
) : AuthDataSource {
    override fun getAuth(): Single<Auth> {
        return db
            .authDao()
            .getAuth()
    }

    override fun addAuth(auth: Auth): Completable {
        return db
            .authDao()
            .insertAuth(auth = auth)
    }

    override fun deleteAuth(): Completable {
        return db
            .authDao()
            .deleteAuth()
    }
}