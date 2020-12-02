package kr.dktsudgg.androidarchitecturestudy

import android.os.Bundle
import androidx.databinding.Observable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMovieSearchHistoryBinding
import kr.dktsudgg.androidarchitecturestudy.view.adapter.MovieSearchHistoryListAdapter
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm.MovieSearchHistoryViewModel

class MovieSearchHistoryActivity :
    BaseActivity<ActivityMovieSearchHistoryBinding>(R.layout.activity_movie_search_history) {

    private val movieSearchHistoryViewModel by lazy { MovieSearchHistoryViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = movieSearchHistoryViewModel

        /**
         * 영화 검색이력 리스트 보여주는 RecyclerView에 어댑터 연결 및 목록 구분선 추가
         */
        binding.movieSearchHistoryList.adapter = MovieSearchHistoryListAdapter()
        binding.movieSearchHistoryList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        /**
         * 영화 검색이력 데이터가 갱신되었을 경우, 리싸이클러 뷰에 반영
         */
        movieSearchHistoryViewModel.searchHistoryList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                movieSearchHistoryViewModel.searchHistoryList.get()?.let {
                    (binding.movieSearchHistoryList.adapter as MovieSearchHistoryListAdapter).refreshData(
                        it
                    )
                }
            }

        })

        /**
         * 로컬 저장소로부터 영화 검색 이력 조회
         */
        movieSearchHistoryViewModel.refreshMovieSearchHistoryList()
    }

}