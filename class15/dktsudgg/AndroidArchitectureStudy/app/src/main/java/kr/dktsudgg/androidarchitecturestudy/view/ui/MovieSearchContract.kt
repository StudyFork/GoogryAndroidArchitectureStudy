package kr.dktsudgg.androidarchitecturestudy.view.ui

import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem

interface MovieSearchContract {

    interface View : BaseContract.View {
        /**
         * 영화 검색 결과 갱신 메소드
         */
        fun updateSearchedMovieList(data: List<MovieItem>)

        /**
         * 영화 검색이력 조회결과 갱신 메소드
         */
        fun updateMovieSearchHistoryList(data: List<String>)
    }

    interface Presenter : BaseContract.Presenter {
        /**
         * 영화 검색 메소드
         */
        fun searchMovies(query: String)

        /**
         * 영화 검색이력 조회 메소드
         */
        fun showMovieSearchHistory()
    }

}