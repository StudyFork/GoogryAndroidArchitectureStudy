package com.hwaniiidev.architecture.data.source.remote

import com.hwaniiidev.architecture.ApiCall
import com.hwaniiidev.architecture.model.ResponseMovieSearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverMovieRemoteDataSourceImpl : NaverMovieRemoteDataSource {
    private val api = ApiCall.api

    override fun getRemoteMovies(
        query: String,
        //onSuccess: (movies: ArrayList<Item>) -> Unit,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        api.getMovieSearchData(clientId, clientPw, query, displayValue)
            .enqueue(object : Callback<ResponseMovieSearchData> {
                override fun onResponse(
                    call: Call<ResponseMovieSearchData>,
                    response: Response<ResponseMovieSearchData>
                ) {
                    if (response.isSuccessful) {
                        var result = response.body()
                        if (result != null) {
                            result.query = query
                            onSuccess(result)
                            //onSuccess(result.items)
                        }
                    } else {
                        onError(response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ResponseMovieSearchData>, t: Throwable) {
                    onFailure(t)
                }
            })
    }

    companion object {
        const private val clientId = "KXY8b7w9cuaFFHkDSGwS"
        const private val clientPw = "HdI9WbTqtt"
        const private val displayValue = 30
    }
}