package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.Observable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieSearchBinding
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import com.example.googryandroidarchitecturestudy.ui.viewmodel.MovieSearchViewModel
import kotlinx.coroutines.launch

class MovieSearchFragment :
    BaseFragment<FragmentMovieSearchBinding, MovieSearchViewModel>(R.layout.fragment_movie_search) {
    private val viewModel by lazy {
        MovieSearchViewModel(requireContext())
    }

    private val movieAdapter = MovieAdapter {
        viewModel.selectUrlItem(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.movieList.adapter = movieAdapter
        binding.v = this
        binding.vm = viewModel

        viewModel.apply {
            showQueryEmptyEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showQueryEmpty()
                }
            })

            showNoSearchResultEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showNoSearchResult()
                }
            })

            showSearchMovieFailedEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showSearchMovieFailedEvent.get()?.let { showSearchFailed(it) }
                }
            })

            showSaveRecentFailedEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showSaveRecentFailedEvent.get()?.let { showSaveRecentFailed(it) }
                }
            })
        }
    }

    fun queryMovieList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.queryMovieList(binding.searchText.text.toString())
        }
    }

    fun showMovieList(items: List<Movie>) {
        movieAdapter.setMovies(items)
    }

    private fun showQueryEmpty() {
        toast(getString(R.string.no_keyword))
        movieAdapter.setMovies(listOf())
    }

    fun showNoSearchResult() {
        toast(getString(R.string.no_results))
        movieAdapter.setMovies(listOf())
    }

    fun showSearchFailed(e: Exception) {
        toast(getString(R.string.error_occurred))
        Log.e(TAG, "Searching movies from keyword failed: ${e.message.toString()}")
    }

    fun showSaveRecentFailed(e: Exception) {
        Log.e(TAG, "Saving recent keyword failed: ${e.message.toString()}")
    }

    fun navToRecentSearch() {
//        val action = MovieFragmentDirections.actionMovieFragmentToRecentFragment()
        val action = MovieSearchFragmentDirections.actionMovieFragmentToRecentFragment()
        this.findNavController().navigate(action)
    }

    companion object {
        private val TAG = MovieSearchFragment::class.java.simpleName
    }
}