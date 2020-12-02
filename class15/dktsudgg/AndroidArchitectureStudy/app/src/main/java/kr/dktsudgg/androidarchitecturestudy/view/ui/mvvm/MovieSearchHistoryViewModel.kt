package kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm

import androidx.databinding.ObservableField
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepositoryImpl

class MovieSearchHistoryViewModel : BaseViewModel() {

    private val naverMovieRepository: NaverMovieRepository = NaverMovieRepositoryImpl()

    /**
     * 영화 검색 이력을 담고 있는 변수
     */
    val searchHistoryList = ObservableField<List<String>>()

    fun refreshMovieSearchHistoryList() {
        searchHistoryList.set(
            naverMovieRepository.getMovieSearchHistory()
        )
        searchHistoryList.notifyChange()
    }

}