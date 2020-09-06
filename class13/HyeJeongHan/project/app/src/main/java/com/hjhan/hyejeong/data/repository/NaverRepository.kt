package com.hjhan.hyejeong.data.repository

import com.hjhan.hyejeong.data.model.MovieData

interface NaverRepository {

    //영화 리스트 가져오기
    fun getSearchMovies(query: String,
                        success: (MovieData) -> Unit,
                        failed: (String) -> Unit)

    //쿼리 저장
    fun saveQuery(query: String)

    //쿼리 리스트 불러오기
    fun getQueryList(): List<String>

}