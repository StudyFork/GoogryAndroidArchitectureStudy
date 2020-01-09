package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import com.god.taeiim.myapplication.BR
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResultShow
import com.god.taeiim.myapplication.base.BaseFragment
import com.god.taeiim.myapplication.data.source.NaverRepositoryImpl
import com.god.taeiim.myapplication.data.source.local.NaverLocalDataSourceImpl
import com.god.taeiim.myapplication.data.source.local.SearchHistoryDatabase
import com.god.taeiim.myapplication.data.source.remote.NaverRemoteDataSourceImpl
import com.god.taeiim.myapplication.databinding.FragmentMainBinding
import com.god.taeiim.myapplication.databinding.ItemContentsBinding

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

    lateinit var searchResultAdapter: SearchResultRecyclerAdapter<SearchResultShow.Item, ItemContentsBinding>
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

        searchResultAdapter =
            SearchResultRecyclerAdapter(searchType, R.layout.item_contents, BR.item)
        binding.searchResultRecyclerView.adapter = searchResultAdapter

        updateSearchHistoryItems()

        with(binding) {
            searchBtn.setOnClickListener {
                presenter.searchContents(searchType, searchEditTv.text.toString())
            }
        }
    }

    override fun updateSearchHistoryItems() {
        presenter.getLastSearchHistory(searchType)
    }

    override fun updateItems(resultList: List<SearchResultShow.Item>) {
        searchResultAdapter.updateItems(resultList)
    }

    override fun failToSearch() {
        searchResultAdapter.clearItems()
        Toast.makeText(context, getString(R.string.err_search), Toast.LENGTH_SHORT).show()
    }

    override fun blankSearchQuery() {
        searchResultAdapter.clearItems()
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
