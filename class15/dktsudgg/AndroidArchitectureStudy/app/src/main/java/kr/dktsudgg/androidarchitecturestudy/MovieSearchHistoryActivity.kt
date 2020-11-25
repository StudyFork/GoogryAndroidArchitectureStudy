package kr.dktsudgg.androidarchitecturestudy

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_search_history.*
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepositoryImpl
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMovieSearchHistoryBinding
import kr.dktsudgg.androidarchitecturestudy.view.adapter.MovieSearchHistoryListAdapter
import kr.dktsudgg.androidarchitecturestudy.view.ui.MovieContract
import kr.dktsudgg.androidarchitecturestudy.view.ui.MoviePresenter

class MovieSearchHistoryActivity : BaseActivity<MoviePresenter>(), MovieContract.View {

    private lateinit var binding: ActivityMovieSearchHistoryBinding
    private lateinit var moviePresenter: MovieContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_search_history)

        moviePresenter = MoviePresenter(NaverMovieRepositoryImpl(), this)

        /**
         * 영화 검색이력 리스트 보여주는 RecyclerView에 어댑터 연결 및 목록 구분선 추가
         */
        movieSearchHistoryList.adapter = MovieSearchHistoryListAdapter()
        movieSearchHistoryList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        /**
         * 로컬 저장소로부터 영화 검색 이력 조회해옴
         */
        moviePresenter.showMovieSearchHistory()
    }

    override fun updateMovieSearchHistoryList(data: List<String>) {
        (movieSearchHistoryList.adapter as MovieSearchHistoryListAdapter).refreshData(data)
    }

    override fun updateSearchedMovieList(data: List<MovieItem>) {
        TODO("Not yet implemented")
    }


}