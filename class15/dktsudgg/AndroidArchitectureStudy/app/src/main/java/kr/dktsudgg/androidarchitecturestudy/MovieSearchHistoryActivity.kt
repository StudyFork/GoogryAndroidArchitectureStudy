package kr.dktsudgg.androidarchitecturestudy

import android.os.Bundle
import androidx.activity.viewModels
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMovieSearchHistoryBinding
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm.MovieSearchHistoryViewModel

class MovieSearchHistoryActivity :
    BaseActivity<ActivityMovieSearchHistoryBinding>(R.layout.activity_movie_search_history) {

    private val movieSearchHistoryViewModel: MovieSearchHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = movieSearchHistoryViewModel
        binding.lifecycleOwner = this
    }

}