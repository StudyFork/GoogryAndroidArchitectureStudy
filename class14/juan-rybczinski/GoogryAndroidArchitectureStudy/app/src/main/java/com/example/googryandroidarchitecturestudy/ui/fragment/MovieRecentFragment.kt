package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieRecentBinding
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.ui.viewmodel.MovieRecentViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class MovieRecentFragment :
    BaseFragment<FragmentMovieRecentBinding, MovieRecentViewModel>(R.layout.fragment_movie_recent) {
    private val viewModel by lazy {
        MovieRecentViewModel(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.v = this
        binding.vm = viewModel

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.getRecentKeywords()
            } catch (e: Exception) {
                Log.e(TAG, "Getting recent search keywords failed: ${e.message.toString()}")
            }
        }

    }

    fun showRecentChips(recentList: List<RecentSearch>) {
        recentList.forEach {
            binding.recentChipGroup.addView(Chip(requireContext()).apply {
                text = it.query
                isCheckable = true
//                setOnClickListener {
//                    viewLifecycleOwner.lifecycleScope.launch {
//                        presenter.queryMovieList(this@apply.text.toString())
//                    }
//                }
            })
        }
    }

    companion object {
        private val TAG = MovieRecentFragment::class.java.simpleName
    }

}