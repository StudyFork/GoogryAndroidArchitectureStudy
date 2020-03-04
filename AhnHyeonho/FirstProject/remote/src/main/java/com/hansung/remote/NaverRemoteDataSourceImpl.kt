package com.hansung.remote

import android.content.Context
import com.hansung.remote.data.ErrorStringResource
import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.remote.api.NaverApiServiceImpl
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException


class NaverRemoteDataSourceImpl(
    private val stringContext: Context
) : NaverRemoteDataSource {

    override fun getMoviesData(
        title: String,
        onResponse: (MovieResponseModel) -> Unit,
        onFailure: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    ) {
        NaverApiServiceImpl.getResult(
            stringContext.getString(R.string.client_id),
            stringContext.getString(R.string.client_secret),
            title,
            100
        )
            .enqueue(object : retrofit2.Callback<MovieResponseModel> {
                override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                    when (t) {
                        is UnknownHostException -> onFailure(Throwable(ErrorStringResource.INTERNET_ERROR.name))
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
                        } else {
                            onResponse(response.body()!!)
                        }
                    } else {
                        response.code().let {
                            when (it) {
                                400 -> onFailure(Throwable(ErrorStringResource.ERROR_CODE_400.name))
                                401 -> onFailure(Throwable(ErrorStringResource.ERROR_CODE_401.name))
                                500 -> onFailure(Throwable(ErrorStringResource.ERROR_CODE_500.name))
                            }
                        }
                    }
                }
            })
    }
}