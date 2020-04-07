package io.github.sooakim.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityMovieSearchBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.adapter.SAMovieSearchResultAdapter

class SAMovieSearchActivity :
    SAActivity<ActivityMovieSearchBinding, SAMovieSearchViewModel, SAMovieSearchState>(
        layoutResId = R.layout.activity_movie_search
    ) {
    override val viewModel: SAMovieSearchViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SAMovieSearchViewModel(
                    movieRepository = requireApplication().movieRepository
                ) as T
            }
        }
    }

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
