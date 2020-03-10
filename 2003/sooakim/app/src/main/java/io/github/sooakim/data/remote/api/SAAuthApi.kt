package io.github.sooakim.data.remote.api

import io.github.sooakim.data.remote.model.request.SAAuthRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface SAAuthApi {
    @POST("/auth")
    fun postLogin(@Body requestBody: SAAuthRequest): Completable
}