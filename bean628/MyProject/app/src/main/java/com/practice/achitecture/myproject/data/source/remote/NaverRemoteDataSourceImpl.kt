package com.practice.achitecture.myproject.data.source.remote

import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import com.practice.achitecture.myproject.network.RetrofitClient
import common.NAVER_API_BASE_URL
import common.NETWORK_ERROR_MSG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    private val naverApiService = RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()

    override fun searchWordByNaver(
        category: String,
        word: String,
        callBack: NaverRemoteDataSource.GetResultOfSearchingCallBack
    ) {
        val call = naverApiService.searchSomething(category, word)
        //To change body of created functions use File | Settings | File Templates.
        call.enqueue(object : Callback<ResultOfSearchingModel> {
            override fun onResponse(
                call: Call<ResultOfSearchingModel>,
                response: Response<ResultOfSearchingModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items?.let {
                        callBack.onSuccess(it)
                    } ?: run {
                        callBack.onSuccess(listOf())
                    }
                } else {
                    callBack.onFailure(NETWORK_ERROR_MSG)
                }
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {
                callBack.onFailure(NETWORK_ERROR_MSG)
            }
        })
    }


}