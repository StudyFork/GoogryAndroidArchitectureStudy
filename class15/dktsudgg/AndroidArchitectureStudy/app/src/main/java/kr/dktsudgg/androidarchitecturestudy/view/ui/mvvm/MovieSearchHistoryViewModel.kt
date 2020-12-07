package kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepositoryImpl

class MovieSearchHistoryViewModel : BaseViewModel() {

    private val naverMovieRepository: NaverMovieRepository = NaverMovieRepositoryImpl()

    /**
     * 영화 검색 이력을 담고 있는 변수
     */
    private val _searchHistoryList = MutableLiveData<List<String>>()
    val searchHistoryList: LiveData<List<String>> = _searchHistoryList

    init {
        refreshMovieSearchHistoryList()
    }

    /**
     * 로컬 저장소로부터 영화 검색 이력 조회하여 데이터 갱신
     */
    fun refreshMovieSearchHistoryList() {
        _searchHistoryList.value = naverMovieRepository.getMovieSearchHistory()
    }

}