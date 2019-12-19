package com.practice.achitecture.myproject.data.source.local

import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.util.AppExecutors

class NaverLocalDataSourceImpl private constructor(
    val appExcutors: AppExecutors,
    val naverDao: NaverDao
) : NaverDataSource {

    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callBack: NaverDataSource.GettingResultOfSearchingCallBack
    ) {
        // TODO : 4.1 추가 과제 검색했던 결과 히스토리 (검색할때마다 저장해서 히스토리를 남김)
        //         할 때 Room 사용과 함께 쓸 계획입니다.
    }


    companion object {
        private var INSTANCE: NaverLocalDataSourceImpl? = null

        fun getInstance(appExcutors: AppExecutors, naverDao: NaverDao): NaverLocalDataSourceImpl {
            if (INSTANCE == null) {
                synchronized(NaverLocalDataSourceImpl::javaClass) {
                    INSTANCE = NaverLocalDataSourceImpl(appExcutors, naverDao)
                }
            }
            return INSTANCE!!
        }
    }

}