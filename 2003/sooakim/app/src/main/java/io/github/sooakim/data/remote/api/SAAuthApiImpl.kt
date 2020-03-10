package io.github.sooakim.data.remote.api

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.github.sooakim.data.remote.model.request.SAAuthRequest
import io.reactivex.Completable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class SAAuthApiImpl : SAAuthApi {
    override fun postLogin(requestBody: SAAuthRequest): Completable {
        return Completable.defer {
            when {
                requestBody.id != TEST_ID -> {
                    Completable.error(HttpException(provideErrorResponse(404)))
                }
                requestBody.password != TEST_PW -> {
                    Completable.error(HttpException(provideErrorResponse(409)))
                }
                else -> {
                    Completable.complete()
                }
            }
        }
    }

    private fun provideErrorResponse(code: Int): Response<Unit> {
        return Response.error(code, provideErrorBody())
    }

    private fun provideErrorBody(): ResponseBody {
        return "{ message: 'invalid_information' }"
            .toResponseBody("application/json".toMediaTypeOrNull())
    }

    companion object {
        private const val TEST_ID = "id"
        private const val TEST_PW = "P@ssw0rd"
    }
}