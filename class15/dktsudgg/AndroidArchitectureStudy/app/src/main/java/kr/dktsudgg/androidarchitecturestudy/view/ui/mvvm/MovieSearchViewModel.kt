package kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm

import androidx.databinding.ObservableField
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepository
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepositoryImpl

class MovieSearchViewModel : BaseViewModel() {

    private val naverMovieRepository: NaverMovieRepository = NaverMovieRepositoryImpl()

    /**
     * 영화 검색이력 목록 보여주기 요청이 발생할 시, 알리기 위한 변수
     */
    val eventToShowMovieSearchHistory = ObservableField<Unit>()

    /**
     * 영화 검색 결과 목록을 담고 있는 변수
     */
    val movieList = ObservableField<List<MovieItem>>()

    fun refreshSearchMovieList(query: String){
        if (isEmptyData(query)) {
            eventWhenEmptyInputInjected.notifyChange()
            return
        }

        naverMovieRepository.searchMovies(query, {
            (it as NaverMovieResponse).items?.let { searchedMovieList ->
                // 검색된 영화 데이터로 갱신
                movieList.set(searchedMovieList)
                movieList.notifyChange()
                eventWhenDataRefreshRequestSuccess.notifyChange()
            }
        }, {
            // 빈 데이터로 갱신
            movieList.set(ArrayList<MovieItem>())
            movieList.notifyChange()
            eventWhenDataRefreshRequestFailure.notifyChange()
        })
    }

    /**
     * 영화 검색이력 조회 요청 이벤트
     */
    fun showMovieSearchHistory(){
        eventToShowMovieSearchHistory.notifyChange()
    }

}