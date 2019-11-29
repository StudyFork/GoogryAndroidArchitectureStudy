package com.egiwon.architecturestudy.data.source.remote

import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.service.RetrofitApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaverRemoteDataSource : NaverDataSource {

    override fun getContents(
        type: String,
        query: String
    ): Single<Content> =
        RetrofitApi.retrofit.getContentsInfo(
            type = type,
            query = query
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    companion object {
        private var instance: NaverRemoteDataSource? = null

        fun getInstance() = instance ?: NaverRemoteDataSource()
            .apply { instance = this }
    }
}