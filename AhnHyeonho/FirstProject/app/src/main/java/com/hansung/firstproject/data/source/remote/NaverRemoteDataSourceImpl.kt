package com.hansung.firstproject.data.source.remote

import android.content.res.Resources
import com.hansung.firstproject.R
import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.source.remote.api.NaverApiServiceImpl
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException


class NaverRemoteDataSourceImpl private constructor(
    private val stringRes: Resources
) : NaverRemoteDataSource {

    override fun getMoviesData(
        title: String,
        onResponse: (MovieResponseModel) -> Unit,
        onFailure: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    ) {
        NaverApiServiceImpl.getResult(
            stringRes.getString(R.string.client_id),
            stringRes.getString(R.string.client_secret),
            title,
            100
        )
            .enqueue(object : retrofit2.Callback<MovieResponseModel> {
                override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                    when (t) {
                        is UnknownHostException -> onFailure(Throwable(stringRes.getString(R.string.internet_error_message)))
                        is Exception -> onFailure(t)
                    }
                }

                override fun onResponse(
                    call: Call<MovieResponseModel>,
                    response: Response<MovieResponseModel>
                ) {

                    if (response.isSuccessful) {
                        if (response.body()!!.display == 0) {
                            isEmptyList()
                        } else
                            onResponse(response.body()!!)
                    } else {
                        response.code().let {
                            when (it) {
                                400 -> onFailure(Throwable(stringRes.getString(R.string.error_message_400)))
                                401 -> onFailure(Throwable(stringRes.getString(R.string.error_message_401)))
                                500 -> onFailure(Throwable(stringRes.getString(R.string.error_message_500)))
                            }
                        }
                    }
                }
            })
    }

    companion object {
        @Volatile
        private var _INSTANCE: NaverRemoteDataSourceImpl? = null

        @JvmStatic
        fun getInstance(stringRes: Resources): NaverRemoteDataSourceImpl =
            _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: NaverRemoteDataSourceImpl(stringRes).also {
                    _INSTANCE = it
                }
            }
    }
}