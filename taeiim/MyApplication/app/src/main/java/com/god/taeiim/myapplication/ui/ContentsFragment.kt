package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.NaverRepositoryImpl
import kotlinx.android.synthetic.main.fragment_main.*

class ContentsFragment : Fragment() {
    private lateinit var adapter: SearchResultRecyclerAdapter
    private var searchType = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getString(ARG_TYPE)?.let {
            searchType = it
        }
        adapter = SearchResultRecyclerAdapter(searchType)
        searchResultRecyclerView.adapter = this@ContentsFragment.adapter

        searchBtn.setOnClickListener {
            searchEditTv.text.toString().let { searchBlog(it) }
        }
    }

    private fun searchBlog(query: String) {
        NaverRepositoryImpl.getResultData(
            searchType,
            query,
            success = { refreshItems(it) },
            fail = { failToSearch() }
        )
    }

    private fun refreshItems(resultList: SearchResult) {
        adapter.setItems(resultList.items)
    }

    private fun failToSearch() {
        adapter.clearItems()
        Toast.makeText(context, getString(R.string.err_search), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARG_TYPE = ""

        fun newInstance(type: String) =
            ContentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TYPE, type)
                }
            }

    }
}