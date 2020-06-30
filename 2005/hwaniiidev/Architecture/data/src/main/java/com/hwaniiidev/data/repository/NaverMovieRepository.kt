package com.hwaniiidev.data.repository

import com.hwaniiidev.data.model.Item
import com.hwaniiidev.data.model.ResponseMovieSearchData

interface NaverMovieRepository {

    /**
     * 영화 검색.
     * 로컬에서 먼저 검색 후 로컬에 결과가 있으면 로컬 데이터를 바로 출력
     * 없으면 서버에서 검색
     */
    fun searchMovies(
        query: String,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit,
        onCached: (movies: List<Item>?) -> Unit
    )

    /**
     * 서버에서 영화검색
     * 검색 후 로컬 DB에 중복되지 않게 저장
     */
    fun getRemoteMovies(
        query: String,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

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