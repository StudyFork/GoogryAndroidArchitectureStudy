package kr.dktsudgg.androidarchitecturestudy

import android.os.Bundle
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMovieSearchHistoryBinding
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm.MovieSearchHistoryViewModel

class MovieSearchHistoryActivity :
    BaseActivity<ActivityMovieSearchHistoryBinding>(R.layout.activity_movie_search_history) {

    private val movieSearchHistoryViewModel by lazy { MovieSearchHistoryViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = movieSearchHistoryViewModel

        /**
         * 로컬 저장소로부터 영화 검색 이력 조회
         */
        movieSearchHistoryViewModel.refreshMovieSearchHistoryList()
    }

}