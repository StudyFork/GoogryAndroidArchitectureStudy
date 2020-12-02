package kr.dktsudgg.androidarchitecturestudy.view.ui.mvp

interface MovieHistoryContract {

    interface View {
        /**
         * 영화 검색이력 조회결과 갱신 메소드
         */
        fun updateMovieSearchHistoryList(data: List<String>)
    }

    interface Presenter {
        /**
         * 영화 검색이력 조회 메소드
         */
        fun showMovieSearchHistory()
    }

}