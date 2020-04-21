package io.github.sooakim.remote.api

import io.github.sooakim.remote.model.request.SAAuthRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

internal interface SAAuthApi {
    @POST("/auth")
    fun postLogin(@Body requestBody: SAAuthRequest): Completable
}