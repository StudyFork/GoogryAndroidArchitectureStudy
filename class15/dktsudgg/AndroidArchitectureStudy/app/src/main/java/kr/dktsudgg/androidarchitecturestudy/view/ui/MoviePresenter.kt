package kr.dktsudgg.androidarchitecturestudy.view.ui

import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository

class MoviePresenter(
    private val naverMovieRepository: NaverMovieRepository,
    private val view: MovieContract.View
) : BasePresenter(), MovieContract.Presenter {

    override fun searchMovies(query: String) {
        if (isEmptyData(query)) view.doWhenUseEmptyData("검색어를 입력하세요")

        naverMovieRepository.searchMovies(query, {
            (it as NaverMovieResponse).items?.let { it1 ->
                view.showSearchedMovieList(it1)
                view.doSuccessAction("검색에 성공하였습니다.")
            }
        }, {
            view.doFailureAction("검색에 실패하였습니다.")
        })
    }
}