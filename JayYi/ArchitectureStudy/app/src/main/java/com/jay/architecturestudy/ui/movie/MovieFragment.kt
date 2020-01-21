package com.jay.architecturestudy.ui.movie

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.databinding.FragmentMovieBinding
import com.jay.architecturestudy.ui.BaseFragment


class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>(R.layout.fragment_movie) {

    override val viewModel: MovieViewModel by lazy {
        MovieViewModel(naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            movieAdapter = MovieAdapter()
            binding.recyclerView.run {
                adapter = movieAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        binding.vm = viewModel
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}