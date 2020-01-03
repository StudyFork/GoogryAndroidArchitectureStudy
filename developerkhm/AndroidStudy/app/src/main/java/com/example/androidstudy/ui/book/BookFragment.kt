package com.example.androidstudy.ui.book

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.example.androidstudy.R
import com.example.androidstudy.databinding.FragmentBookBinding
import com.example.androidstudy.ui.base.BaseFragment
import com.ironelder.androidarchitecture.view.AdapterSearch

class BookFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {

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
                    basePresenter.search(searchEditText.text.toString(), typeArray[3])
                }
                else -> false
            }
            true
        }

        binding.searchLayout.resultRecyclerView.adapter =
            AdapterSearch(context, arrayListOf(), "book")
    }
}