package com.practice.achitecture.myproject.data.source.local

import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.enum.SearchType

class NaverLocalDataSourceImpl : NaverDataSource {
    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callBack: NaverDataSource.GettingResultOfSearchingCallBack
    ) {

    }


}