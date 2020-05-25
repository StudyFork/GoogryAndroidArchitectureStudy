package com.hwaniiidev.architecture.ui.main

import com.hwaniiidev.architecture.model.Item

interface MainContract {
    interface View {
        /**
         * 검색어가 비어있을 경우 Toast 메세지 출력
         */
        fun showQueryIsEmpty()

        /**
         * 검색결과가 있을 경우 키보드 숨기기
         */
        fun hideKeyBoard()

        /**
         * 검색결과가 없을 경우 Toast 메세지 출력
         */
        fun showResponseIsNone()

        /**
         * 서버 에러 Toast 메세지 출력
         */
        fun showResponseError()

        /**
         * 로컬 네트워크 에러 Toast 메세지 출력
         */
        fun showNetworkFailure()

        /**
         * 영화 검색 결과 리스트 출력
         * @param items 영화 리스트
         */
        fun showMoviesList(items: List<Item>)
    }

    interface Presenter {
        /**
         * 영화 local 및 remote 에서 검색트
         * @param query 검색 키워드
         */
        fun searchMovies(query: String)
    }
}