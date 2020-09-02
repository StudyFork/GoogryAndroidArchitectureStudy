package com.hjhan.hyejeong.data.source.remote

interface NaverRemoteDataSource {

    //영화 리스트 가져오기
    fun getSearchMovies(query: String)

}