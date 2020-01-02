package com.example.androidstudy.ui.news

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudy.R
import com.example.androidstudy.ui.base.BaseFragment
import com.ironelder.androidarchitecture.view.AdapterSearch

class NewsFragment : BaseFragment(R.layout.fragment_news) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setLayout()
    }

    private fun setLayout() {

        searchButton.setOnClickListener {
            searchEditText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }

        searchEditText.setOnEditorActionListener { searchEditText, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    basePresenter.search(searchEditText.text.toString(), typeArray[1])
                }
                else -> false
            }
            true
        }

        resultRecyclerView.adapter = AdapterSearch(context, arrayListOf(), "news")
        resultRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

}