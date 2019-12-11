package com.practice.achitecture.myproject.data.source.local

import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.enum.SearchType

class NaverLocalDataSourceImpl : NaverDataSource {
    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callBack: NaverDataSource.GettingResultOfSearchingCallBack
    ) {
        // TODO : 4.1 추가 과제 검색했던 결과 히스토리 (검색할때마다 저장해서 히스토리를 남김)
        //         할 때 Room 사용과 함께 쓸 계획입니다.
    }


}