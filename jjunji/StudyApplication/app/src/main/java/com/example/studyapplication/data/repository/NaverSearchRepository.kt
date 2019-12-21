package com.example.studyapplication.data.repository

import com.example.studyapplication.network.Conn

interface NaverSearchRepository {
    // 영화 조회 결과 가져오기
    fun getMovieList(query : String, conn : Conn)

    // 블로그 조회 결과 가져오기
    fun getBlogList(query: String, conn : Conn)

    // 이미지 검색 결과 가져오기
    fun getImageList(query: String, conn : Conn)

    // 지식인 검색 결과 가져오기
    fun getKinList(title : String, conn : Conn)
}