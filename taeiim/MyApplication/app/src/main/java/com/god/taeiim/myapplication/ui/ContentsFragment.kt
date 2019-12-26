package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.base.BaseFragment
import com.god.taeiim.myapplication.data.source.NaverRepositoryImpl
import com.god.taeiim.myapplication.data.source.local.NaverLocalDataSourceImpl
import com.god.taeiim.myapplication.data.source.local.SearchHistoryDatabase
import com.god.taeiim.myapplication.data.source.remote.NaverRemoteDataSourceImpl
import com.god.taeiim.myapplication.databinding.FragmentMainBinding

class ContentsFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main),
    ContentsContract.View {

    override val presenter: ContentsContract.Presenter by lazy {
        ContentsPresenter(
            NaverRepositoryImpl.getInstance(
                NaverRemoteDataSourceImpl,
                NaverLocalDataSourceImpl.getInstance(
                    SearchHistoryDatabase.getInstance(requireActivity().applicationContext).taskDao()
                )
            ), this
        )
    }
    private lateinit var adapter: SearchResultRecyclerAdapter
    private lateinit var searchType: Tabs

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getSerializable(ARG_TYPE)?.let {
            searchType = it as Tabs
        }

        adapter = SearchResultRecyclerAdapter(searchType)
        searchResultRecyclerView.adapter = this@ContentsFragment.adapter

        updateSearchHistoryItems()

    }

    override fun updateSearchHistoryItems() {
        presenter.getLastSearchHistory(searchType.name)
    }

    override fun updateItems(resultList: List<SearchResult.Item>) {
        adapter.setItems(resultList)
    }

    override fun failToSearch() {
        adapter.clearItems()
        Toast.makeText(context, getString(R.string.err_search), Toast.LENGTH_SHORT).show()
    }

    override fun blankSearchQuery() {
        adapter.clearItems()
        Toast.makeText(context, getString(R.string.blank_search), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARG_TYPE = "type"

        fun newInstance(type: Tabs) =
            ContentsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_TYPE, type)
                }
            }

    }
}
