package io.github.sooakim.network.api

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.github.sooakim.network.model.request.SAAuthRequest
import io.reactivex.Completable
import okhttp3.MediaType
import okhttp3.ResponseBody
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
        return ResponseBody.create(
            MediaType.parse("application/json"),
            "{ message: 'invalid_information' }"
        )
    }

    companion object {
        private const val TEST_ID = "id"
        private const val TEST_PW = "P@ssw0rd"
    }
}