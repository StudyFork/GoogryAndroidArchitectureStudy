package com.example.data.source.local

import com.example.data.model.Auth
import io.reactivex.Completable
import io.reactivex.Single

interface AuthLocalDataSource {
    fun getAuth(): Single<Auth>
    fun addAuth(auth: Auth): Completable
    fun deleteAuth(): Completable
}