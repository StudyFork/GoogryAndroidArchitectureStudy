package kr.dktsudgg.androidarchitecturestudy.view.ui

import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem

interface MovieContract {

    interface View {
        /**
         * 영화 검색 결과 갱신 메소드
         */
        fun showSearchedMovieList(data: List<MovieItem>)

        /**
         * 검색 실패 시, 뷰에서 보여줄 내용 메소드
         */
        fun doFailureAction(message: String)
    }

    interface Presenter {
        /**
         * 영화 검색 메소드
         */
        fun searchMovies(query: String)
    }

}