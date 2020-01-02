package com.example.androidstudy.ui.movie

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.example.androidstudy.R
import com.example.androidstudy.databinding.FragmentMovieBinding
import com.example.androidstudy.ui.base.BaseFragment
import com.ironelder.androidarchitecture.view.AdapterSearch

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setLayout()
    }

    private fun setLayout() {

        binding.searchLayout.searchEditText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)

        binding.searchLayout.searchEditText.setOnEditorActionListener { searchEditText, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    basePresenter.search(searchEditText.text.toString(), typeArray[2])
                }
                else -> false
            }
            true
        }

        binding.searchLayout.resultRecyclerView.adapter =
            AdapterSearch(context, arrayListOf(), "movie")

    }
}
