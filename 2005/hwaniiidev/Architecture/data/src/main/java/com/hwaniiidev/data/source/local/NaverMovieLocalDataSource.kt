package com.hwaniiidev.data.source.local

import com.hwaniiidev.data.model.Item

interface NaverMovieLocalDataSource {

    /**
     * 로컬 DB에 서버에서 검색한 결과 저장
     */
    fun cachingMovies(
        query: String,
        movies: ArrayList<Item>
    )

    /**
     * 로컬 DB에서 검색 결과 조회
     */
    fun getCachedMovies(
        query: String,
        onCached: (movies: List<Item>?) -> Unit
    )
}