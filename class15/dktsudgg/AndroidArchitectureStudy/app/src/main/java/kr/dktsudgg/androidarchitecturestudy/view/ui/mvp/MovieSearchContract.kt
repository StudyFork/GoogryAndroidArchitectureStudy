package kr.dktsudgg.androidarchitecturestudy.view.ui.mvp

import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem

interface MovieSearchContract {

    interface View : BaseContract.View {
        /**
         * 영화 검색 결과 갱신 메소드
         */
        fun updateSearchedMovieList(data: List<MovieItem>)
    }

    interface Presenter : BaseContract.Presenter {
        /**
         * 영화 검색 메소드
         */
        fun searchMovies(query: String)
    }

}