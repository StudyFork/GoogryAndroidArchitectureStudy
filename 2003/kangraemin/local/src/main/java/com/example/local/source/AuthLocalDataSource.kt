package com.example.local.source

import com.example.local.model.Auth
import io.reactivex.Completable
import io.reactivex.Single

interface AuthLocalDataSource {
    fun getAuth(): Single<Auth>
    fun addAuth(auth: Auth): Completable
    fun deleteAuth(): Completable
}