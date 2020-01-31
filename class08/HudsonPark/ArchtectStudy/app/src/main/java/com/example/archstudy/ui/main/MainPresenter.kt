package com.example.archstudy.ui.main

import android.content.Context
import com.example.archstudy.data.repository.NaverQueryRepositoryImpl
import com.example.archstudy.data.source.local.AppDatabase
import com.example.archstudy.data.source.local.NaverQueryLocalDataSourceImpl
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl

class MainPresenter : MainInterface.Presenter {

    private var mView: MainInterface.View? = null

    override fun bindView(view: MainInterface.View) {
        this.mView = view
    }

    override fun unBindView() {
        mView = null
    }

    override fun getRemoteDataByQuery(query: String?) {

    }

    override fun getLocalQuery(): String? {

        return ""
    }

    override fun getLocalData(query: String?) {

        // 검색어가 null 이거나 비었을 경우 오류 메세지 출력
        if (query.isNullOrEmpty()) {
            mView?.showErrorMessage(Throwable("검색어가 비었거나 문제가 있습니다. 다시 입력해주세요"))

            // query가 비어있거나 null 값이 아닐 경우 로컬 DB에 데이터 요청
        } else {
            initNaverQueryRepository().apply {
                RequestLocalQueryAsync().execute().get()
            }
        }
    }

    private fun initNaverQueryRepository(): NaverQueryRepositoryImpl {
        var context: Context? = mView?.let { it.getApplicationContext() }

        val localMovieDao = AppDatabase.getInstance(context!!)?.localMovieDao()
        val searchWordDao = AppDatabase.getInstance(context!!)?.searchWordDao()
        val localData = NaverQueryLocalDataSourceImpl(localMovieDao, searchWordDao)
        val remoteData = NaverQueryRemoteDataSourceImpl()

        return NaverQueryRepositoryImpl(localData, remoteData)
    }
}