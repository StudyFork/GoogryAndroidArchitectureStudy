package kr.dktsudgg.androidarchitecturestudy.view.ui

import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository

class MoviePresenter(
    private val naverMovieRepository: NaverMovieRepository,
    private val view: MovieContract.View
) : MovieContract.Presenter {

    override fun searchMovies(query: String) {
        naverMovieRepository.searchMovies(query, {
            (it as NaverMovieResponse).items?.let { it1 -> view.showSearchedMovieList(it1) }
        }, {
            view.doFailureAction("검색에 실패하였습니다.")
        })
    }
}