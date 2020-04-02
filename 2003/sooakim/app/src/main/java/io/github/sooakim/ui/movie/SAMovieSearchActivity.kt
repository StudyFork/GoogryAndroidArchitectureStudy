package io.github.sooakim.ui.movie

import android.os.Bundle
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityMovieSearchBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.base.SANavigationProviderImpl
import io.github.sooakim.ui.movie.adapter.SAMovieSearchResultAdapter

class SAMovieSearchActivity :
    SAActivity<ActivityMovieSearchBinding, SAMovieSearchViewModel>(R.layout.activity_movie_search) {
    override val viewModel: SAMovieSearchViewModel by lazy {
        SAMovieSearchViewModel(
            movieRepository = requireApplication().movieRepository,
            navigator = SAMovieSearchNavigator(
                SANavigationProviderImpl(this)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.rvSearchResult.adapter = SAMovieSearchResultAdapter()
    }
}
