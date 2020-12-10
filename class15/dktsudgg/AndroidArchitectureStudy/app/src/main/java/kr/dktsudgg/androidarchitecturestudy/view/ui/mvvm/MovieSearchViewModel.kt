package kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepositoryImpl

class MovieSearchViewModel : BaseViewModel() {

    private val naverMovieRepository: NaverMovieRepository = NaverMovieRepositoryImpl()

    /**
     * 영화 검색이력 목록 보여주기 요청이 발생할 시, 알리기 위한 변수
     */
    val eventToShowMovieSearchHistory = MutableLiveData<Unit>()

    /**
     * 영화 검색 결과 목록을 담고 있는 변수
     */
    private val _movieList = MutableLiveData<List<MovieItem>>()
    val movieList: LiveData<List<MovieItem>> = _movieList

    /**
     * 영화 검색 입력값
     */
    val query = MutableLiveData<String>()

    fun refreshSearchMovieList() {

        val searchKeyword = query.value

        if (searchKeyword.isNullOrEmpty()) {
            eventWhenEmptyInputInjected.value = Unit
            return
        }

        naverMovieRepository.searchMovies(searchKeyword, { naverMovieResponse ->
            naverMovieResponse.items?.let { searchedMovieList ->
                // 검색된 영화 데이터로 갱신
                _movieList.value = searchedMovieList
                eventWhenDataRefreshRequestSuccess.value = Unit
            }
        }, {
            // 빈 데이터로 갱신
            _movieList.value = ArrayList<MovieItem>()
            eventWhenDataRefreshRequestFailure.value = Unit
        })

    }

    /**
     * 영화 검색이력 조회 요청 이벤트
     */
    fun showMovieSearchHistory() {
        eventToShowMovieSearchHistory.value = Unit
    }

}