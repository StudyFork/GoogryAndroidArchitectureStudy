package kr.dktsudgg.androidarchitecturestudy.view.ui.mvp

import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository

class MovieHistoryPresenter(
    private val naverMovieRepository: NaverMovieRepository,
    private val view: MovieHistoryContract.View
) : BasePresenter(), MovieHistoryContract.Presenter {

    override fun showMovieSearchHistory() {
        view.updateMovieSearchHistoryList(
            naverMovieRepository.getMovieSearchHistory()
        )
    }

}