package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.googryandroidarchitecturestudy.databinding.FragmentRecentBinding
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter
import com.example.googryandroidarchitecturestudy.ui.presenter.RecentPresenter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class RecentFragment : MovieListFragment<FragmentRecentBinding, BasePresenter>(),
    RecentContract.View {
    override val presenter: RecentPresenter by lazy {
        RecentPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.movieList.adapter = movieAdapter
        binding.view = this

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                presenter.getRecentSearch()
            } catch (e: Exception) {
                Log.e(TAG, "Getting recent search keywords failed: ${e.message.toString()}")
            }
        }

    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentBinding =
        FragmentRecentBinding.inflate(inflater, container, false)

    override fun showRecentChips(recentList: List<RecentSearch>) {
        recentList.forEach {
            binding.recentChipGroup.addView(Chip(requireContext()).apply {
                text = it.query
                isCheckable = true
                setOnClickListener {
                    viewLifecycleOwner.lifecycleScope.launch {
                        presenter.queryMovieList(this@apply.text.toString())
                    }
                }
            })
        }
    }

    override fun showNoSearchResult() {
        super.showNoSearchResult()
        movieAdapter.setMovies(listOf())
    }

    override fun showSearchFailed(e: Exception) {
        super.showSearchFailed(e)
        Log.e(TAG, e.message.toString())
    }

    companion object {
        private val TAG = RecentFragment::class.java.simpleName
    }

}