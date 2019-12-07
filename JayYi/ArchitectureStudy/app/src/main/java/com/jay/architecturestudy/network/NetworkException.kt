package com.jay.architecturestudy.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.jay.architecturestudy.util.peekBody
import okhttp3.Response
import java.lang.Exception

class NetworkException(
    val statusCode: Int,
    val code: String,
    override val message: String,
    cause: Throwable? = null
): RuntimeException(message, cause) {

    companion object {
        fun create(response: Response): NetworkException? {
            val body = response.peekBody()
                ?: return null

            try {
                Gson().fromJson(body, NaverErrorBody::class.java)?.let {
                    if (!it.errorCode.isBlank() || it.errorMessage != null) {
                        return NetworkException(response. code(), it.errorCode, it.errorMessage)
                    }
                }
            } catch (e: Exception) {
                Log.e("NetworkException", "error=%s", e)
            }
            return null
        }
    }

}

data class NaverErrorBody(
    @SerializedName("errorCode") val errorCode: String,
    @SerializedName("errorMessage") val errorMessage: String
)
