package com.practice.achitecture.myproject.data.source.remote

import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import com.practice.achitecture.myproject.network.EmptyDataException
import com.practice.achitecture.myproject.network.ResponseNotSuccessException
import com.practice.achitecture.myproject.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl(private val naverApiService: RetrofitService) :
    NaverRemoteDataSource {

    override fun searchWordByNaver(
        category: String,
        word: String,
        callBack: NaverRemoteDataSource.GettingResultOfSearchingCallBack
    ) {
        val call = naverApiService.searchSomething(category, word)
        //To change body of created functions use File | Settings | File Templates.
        call.enqueue(object : Callback<ResultOfSearchingModel> {
            override fun onResponse(
                call: Call<ResultOfSearchingModel>,
                response: Response<ResultOfSearchingModel>
            ) {
                if (!response.isSuccessful) {
                    callBack.onFailure(ResponseNotSuccessException(R.string.toast_network_error_msg))
                    return
                }
                val body = response.body()
                if (body == null) {
                    callBack.onFailure(EmptyDataException(R.string.toast_empty_result))
                    return
                }
                body.items.ifEmpty {
                    callBack.onFailure(EmptyDataException(R.string.toast_empty_result))
                    return
                }
                callBack.onSuccess(body.items)
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {
                callBack.onFailure(t)
            }
        })
    }

}