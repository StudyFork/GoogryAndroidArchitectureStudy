package com.example.local.source

import androidx.room.EmptyResultSetException
import com.example.common.exception.RoomEmptyResultException
import com.example.data.model.Auth
import com.example.data.source.local.AuthLocalDataSource
import com.example.local.mapper.AuthMapper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class AuthLocalDataSourceImpl(
    private val authDao: AuthDao
) : AuthLocalDataSource {
    override fun getAuth(): Single<Auth> {
        return authDao
            .getAuth()
            .map {
                AuthMapper.localAuthToDataAuth(it)
            }
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext {
                when(it) {
                    is EmptyResultSetException -> {
                        Single.error(RoomEmptyResultException())
                    }
                    else -> {
                        Single.error(it)
                    }
                }
            }
    }

    override fun addAuth(auth: Auth): Completable {
        return authDao
            .insertAuth(auth = AuthMapper.dataAuthToLocalAuth(auth))
            .subscribeOn(Schedulers.io())
    }

    override fun deleteAuth(): Completable {
        return authDao
            .deleteAuth()
            .subscribeOn(Schedulers.io())
    }
}