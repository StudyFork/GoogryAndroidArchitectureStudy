package com.hjhan.hyejeong.data.source.remote

import com.hjhan.hyejeong.data.model.MovieData

interface NaverRemoteDataSource {

    //영화 리스트 가져오기
    fun getSearchMovies(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    )

}