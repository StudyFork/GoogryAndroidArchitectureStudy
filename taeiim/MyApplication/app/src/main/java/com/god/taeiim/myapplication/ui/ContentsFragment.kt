package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.NaverRepositoryImpl
import kotlinx.android.synthetic.main.fragment_main.*

class ContentsFragment(val searchType : String) : Fragment() {
    private val adapter = SearchResultRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(searchResultRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ContentsFragment.adapter
        }

        searchBtn.setOnClickListener {
            searchEditTv.text.toString()?.let { searchBlog(it) }
        }
    }

    private fun searchBlog(query: String) {
        NaverRepositoryImpl.getResultData(
            searchType,
            query,
            success = { successGetSearchResult(it) },
            fail = { errorGetSearchResult() }
        )
    }

    private fun successGetSearchResult(resultList: SearchResult) {
        adapter.setItems(resultList.items)
    }

    private fun errorGetSearchResult() {
        adapter.clearItems()
        Toast.makeText(context, getString(R.string.err_search), Toast.LENGTH_SHORT).show()
    }

}