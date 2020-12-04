package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.Observable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieSearchBinding
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import com.example.googryandroidarchitecturestudy.ui.viewmodel.MovieSearchViewModel
import kotlinx.coroutines.launch

class MovieSearchFragment :
    MovieFragment<FragmentMovieSearchBinding, MovieSearchViewModel>(R.layout.fragment_movie_search) {
    private val args: MovieSearchFragmentArgs by navArgs()

    override val viewModel by lazy {
        MovieSearchViewModel(repository)
    }

    private val movieAdapter = MovieAdapter {
        viewModel.selectUrlItem(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        with(binding) {
            movieList.adapter = movieAdapter
            v = this@MovieSearchFragment
        }

        checkPassedQuery()

        with(viewModel) {
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

            showRecentKeywordsEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (showRecentKeywordsEvent.get() == true) {
                        navToRecentSearch()
                    }
                }
            })
        }
    }

    private fun checkPassedQuery() {
        args.passedQuery?.let {
            queryMovieList(it)
        }
    }

    fun queryMovieList(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.queryMovieList(query)
        }
    }

    private fun showQueryEmpty() {
        toast(getString(R.string.no_keyword))
        movieAdapter.setMovies(listOf())
    }

    private fun showNoSearchResult() {
        toast(getString(R.string.no_results))
        movieAdapter.setMovies(listOf())
    }

    private fun showSearchFailed(e: Exception) {
        toast(getString(R.string.error_occurred))
        Log.e(TAG, "Searching movies from keyword failed: ${e.message.toString()}")
    }

    private fun showSaveRecentFailed(e: Exception) {
        Log.e(TAG, "Saving recent keyword failed: ${e.message.toString()}")
    }

    private fun navToRecentSearch() {
        val action = MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieRecentFragment()
        this.findNavController().navigate(action)
        viewModel.clickRecentButtonCompleted()
    }

    companion object {
        private val TAG = MovieSearchFragment::class.java.simpleName
    }
}