package com.hjhan.hyejeong.data.source.local

interface NaverLocalDataSource {

    //쿼리 저장
    fun saveQuery(query: String)

    //쿼리 리스트 불러오기
    fun getQueryList(): List<String>
}