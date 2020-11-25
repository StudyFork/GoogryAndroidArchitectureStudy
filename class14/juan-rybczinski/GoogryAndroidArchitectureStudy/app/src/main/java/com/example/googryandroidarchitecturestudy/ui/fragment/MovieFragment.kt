package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieBinding
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter
import com.example.googryandroidarchitecturestudy.ui.presenter.MoviePresenter
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import kotlinx.coroutines.launch

class MovieFragment : BaseFragment<FragmentMovieBinding, BasePresenter>(), MovieContract.View {
    private val movieAdapter = MovieAdapter {
        presenter.selectUrlItem(it)
    }

    override val presenter: MoviePresenter by lazy {
        MoviePresenter(this, movieRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.movieList.adapter = movieAdapter
        binding.view = this
    }

    fun queryMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            presenter.queryMovieList(binding.searchText.text.toString())
        }
    }

    override fun showMovieList(items: List<Movie>) {
        movieAdapter.setMovies(items)
    }

    override fun showQueryEmpty() {
        super.showQueryEmpty()
        movieAdapter.setMovies(listOf())
    }

    override fun showNoSearchResult() {
        super.showNoSearchResult()
        movieAdapter.setMovies(listOf())
    }

    override fun showSearchFailed(e: Exception) {
        super.showSearchFailed(e)
        Log.e(TAG, e.message.toString())
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieBinding =
        FragmentMovieBinding.inflate(inflater, container, false)

    override fun navToRecentSearch() {
        val action = MovieFragmentDirections.actionMovieFragmentToRecentFragment()
        this.findNavController().navigate(action)
    }

    companion object {
        private val TAG = MovieFragment::class.java.simpleName
    }
}