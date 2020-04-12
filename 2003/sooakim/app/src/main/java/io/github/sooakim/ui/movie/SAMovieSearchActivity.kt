package io.github.sooakim.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityMovieSearchBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.adapter.SAMovieSearchResultAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SAMovieSearchActivity :
    SAActivity<ActivityMovieSearchBinding, SAMovieSearchViewModel, SAMovieSearchState>(
        layoutResId = R.layout.activity_movie_search
    ) {
    override val viewModel: SAMovieSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.rvSearchResult.adapter = SAMovieSearchResultAdapter()
    }

    override fun onState(newState: SAMovieSearchState) {
        when (newState) {
            is SAMovieSearchState.ShowMovieLink -> {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = newState.movieLink.toUri()
                })
            }
        }
    }
}
