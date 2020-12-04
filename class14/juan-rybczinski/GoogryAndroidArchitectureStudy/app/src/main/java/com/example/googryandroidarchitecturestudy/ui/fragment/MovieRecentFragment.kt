package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.Observable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieRecentBinding
import com.example.googryandroidarchitecturestudy.ui.extension.toast
import com.example.googryandroidarchitecturestudy.ui.viewmodel.MovieRecentViewModel
import kotlinx.coroutines.launch

class MovieRecentFragment :
    BaseFragment<FragmentMovieRecentBinding, MovieRecentViewModel>(R.layout.fragment_movie_recent) {
    override val viewModel by lazy {
        MovieRecentViewModel(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        with(viewModel) {
            showSearchRecentFailedEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showSearchRecentFailedEvent.get()?.let { showSearchRecentFailed(it) }
                }
            })

            showMovieSearchEvent.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (showMovieSearchEvent.get() != "") {
                        navToMovieSearch()
                    }
                }
            })
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRecentKeywords()
        }
    }

    private fun navToMovieSearch() {
        val action =
            MovieRecentFragmentDirections.actionMovieRecentFragmentToMovieSearchFragment(
                viewModel.showMovieSearchEvent.get()
            )
        this@MovieRecentFragment.findNavController().navigate(action)
        viewModel.showMovieSearchEventCompleted()
    }

    private fun showSearchRecentFailed(e: Exception) {
        toast(getString(R.string.error_occurred))
        Log.e(TAG, "Getting recent search keywords failed: ${e.message.toString()}")
    }

    companion object {
        private val TAG = MovieRecentFragment::class.java.simpleName
    }

}