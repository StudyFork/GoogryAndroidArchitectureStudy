package io.github.sooakim.data.remote.source

import io.github.sooakim.data.remote.api.SAAuthApi
import io.github.sooakim.data.remote.model.request.SAAuthRequest
import io.reactivex.Completable

class SAAuthRemoteDataSourceImpl(
    private val authApi: SAAuthApi
) : SAAuthRemoteDataSource {
    override fun login(id: String, password: String): Completable {
        return authApi.postLogin(SAAuthRequest(id, password))
    }
}