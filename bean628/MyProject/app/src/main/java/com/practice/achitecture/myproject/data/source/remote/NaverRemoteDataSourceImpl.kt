package com.practice.achitecture.myproject.data.source.remote

import android.content.res.Resources
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import com.practice.achitecture.myproject.network.RetrofitClient
import common.NAVER_API_BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverRemoteDataSourceImpl : GettingSearchResultsFromNaverDataSource {

    private val naverApiService = RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()

    override fun searchWordByNaver(
        category: String,
        word: String,
        callBack: GettingSearchResultsFromNaverDataSource.GetResultOfSearchingCallBack
    ) {
        val call = naverApiService.searchSomething(category, word)
        //To change body of created functions use File | Settings | File Templates.
        call.enqueue(object : Callback<ResultOfSearchingModel> {
            override fun onResponse(
                call: Call<ResultOfSearchingModel>,
                response: Response<ResultOfSearchingModel>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.items.isEmpty()) {
                            callBack.onSuccessButEmptyData()
                        } else {
                            callBack.onSuccess(response.body()!!.items)
                        }
                    } else {
                        callBack.onSuccessButEmptyData()
                    }

                } else {
                    callBack.onFailure(Resources.getSystem().getString(R.string.toast_network_error_msg))
                }
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {
                callBack.onFailure(t.message.toString())
            }
        })
    }


}