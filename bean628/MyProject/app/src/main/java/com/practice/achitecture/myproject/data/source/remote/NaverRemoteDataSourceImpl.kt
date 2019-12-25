package com.practice.achitecture.myproject.data.source.remote

import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import com.practice.achitecture.myproject.network.EmptyDataException
import com.practice.achitecture.myproject.network.ResponseNotSuccessException
import com.practice.achitecture.myproject.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl(private val naverApiService: RetrofitService) :
    NaverDataSource {

    override fun loadHistoryOfSearch(
        searchType: SearchType,
        callback: NaverDataSource.LoadHistoryOfSearchCallback
    ) {
        // remote에서는 쓰이지 않음
    }

    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callback: NaverDataSource.GettingResultOfSearchingCallback
    ) {
        val call = naverApiService.searchSomething(searchType.value, word)
        //To change body of created functions use File | Settings | File Templates.
        call.enqueue(object : Callback<ResultOfSearchingModel> {
            override fun onResponse(
                call: Call<ResultOfSearchingModel>,
                response: Response<ResultOfSearchingModel>
            ) {
                if (!response.isSuccessful) {
                    callback.onFailure(ResponseNotSuccessException(R.string.toast_network_error_msg))
                    return
                }
                val body = response.body()
                if (body == null) {
                    callback.onFailure(EmptyDataException(R.string.toast_empty_result))
                    return
                }
                body.items.ifEmpty {
                    callback.onFailure(EmptyDataException(R.string.toast_empty_result))
                    return
                }
                callback.onSuccess(body.items)
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

}