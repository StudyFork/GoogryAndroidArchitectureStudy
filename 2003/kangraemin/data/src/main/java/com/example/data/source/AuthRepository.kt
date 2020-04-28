package com.example.data.source

import com.example.data.model.Auth
import io.reactivex.Completable
import io.reactivex.Flowable

interface AuthRepository {
    fun getAuth(): Flowable<Auth>

    fun deleteAuth(): Completable

    fun addAuth(auth: Auth): Completable
}