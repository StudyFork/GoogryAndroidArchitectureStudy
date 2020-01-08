package com.jay.architecturestudy.ui.movie

import android.os.Bundle
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.databinding.FragmentMovieBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.ui.BaseSearchContract
import com.jay.architecturestudy.util.then


class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie),
    MovieContract.View {
    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter

    override var viewType: BaseSearchContract.ViewType =
        BaseSearchContract.ViewType.VIEW_SEARCH_BEFORE
        set(value) {
            if (field != value) {
                field = value
                binding.viewType = value
                binding.invalidateAll()
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            movieAdapter = MovieAdapter()
            binding.recyclerView.run {
                adapter = movieAdapter
            }
        }

        binding.searchBar.onClickAction = { keyword ->
            search(keyword)
        }
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, movies: List<Movie>) {
        keyword.isNotBlank().then {
            binding.searchBar.keyword = keyword

            movies.isNotEmpty().then {
                movieAdapter.setData(movies)
            }

            viewType = when {
                movies.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
                else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
            }

            binding.invalidateAll()
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Movie>) {
        viewType = when {
            result.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
            else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
        }

        if (result.isEmpty()) {
            movieAdapter.clear()
        } else {
            movieAdapter.setData(result)
        }

        binding.invalidateAll()
    }
}