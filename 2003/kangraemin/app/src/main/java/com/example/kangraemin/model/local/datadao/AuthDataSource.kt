package com.example.kangraemin.model.local.datadao

import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Single

interface AuthDataSource {
    fun getAuth(): Single<Auth>
    fun addAuth(auth: Auth): Completable
    fun deleteAuth(): Completable
}