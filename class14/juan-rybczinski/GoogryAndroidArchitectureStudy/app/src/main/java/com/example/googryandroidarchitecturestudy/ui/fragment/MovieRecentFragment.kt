package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieRecentBinding
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.viewmodel.MovieRecentViewModel

class MovieRecentFragment :
    MovieFragment<FragmentMovieRecentBinding, MovieRecentViewModel>(R.layout.fragment_movie_recent) {
    override val viewModel by viewModels<MovieRecentViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieRecentViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        with(viewModel) {
            showSearchRecentFailedEvent.observe(viewLifecycleOwner) {
                showSearchRecentFailed(it)
            }

            showMovieSearchEvent.observe(viewLifecycleOwner) {
                navToMovieSearch(it)
            }
        }
    }

    private fun navToMovieSearch(query: String) {
        val action =
            MovieRecentFragmentDirections.actionMovieRecentFragmentToMovieSearchFragment(query)
        findNavController().navigate(action)
    }

    private fun showSearchRecentFailed(e: Exception) {
        toast(getString(R.string.error_occurred))
        Log.e(TAG, "Getting recent search keywords failed: ${e.message.toString()}")
    }

    companion object {
        private val TAG = MovieRecentFragment::class.java.simpleName
    }

}