package io.github.sooakim.network.api

import io.github.sooakim.network.model.request.SAAuthRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface SAAuthApi {
    @POST("/auth")
    fun postLogin(@Body requestBody: SAAuthRequest): Completable
}