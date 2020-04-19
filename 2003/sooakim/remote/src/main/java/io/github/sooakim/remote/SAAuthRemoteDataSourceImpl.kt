package io.github.sooakim.remote

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.github.sooakim.data.remote.SAAuthRemoteDataSource
import io.github.sooakim.remote.api.SAAuthApi
import io.github.sooakim.remote.exception.InvalidPasswordException
import io.github.sooakim.remote.exception.UserNotFoundException
import io.github.sooakim.remote.model.request.SAAuthRequest
import io.reactivex.Completable

internal class SAAuthRemoteDataSourceImpl(
    private val authApi: SAAuthApi
) : SAAuthRemoteDataSource {
    override fun login(id: String, password: String): Completable {
        return authApi.postLogin(SAAuthRequest(id, password))
            .onErrorResumeNext {
                when (it) {
                    is HttpException -> when (it.code()) {
                        404 -> Completable.error(UserNotFoundException())
                        409 -> Completable.error(InvalidPasswordException())
                        else -> Completable.error(it)
                    }
                    else -> Completable.error(it)
                }
            }
    }
}