package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class ContentsFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private lateinit var vm: ContentsViewModel
    lateinit var searchResultAdapter: SearchResultRecyclerAdapter<SearchResultShow.Item, ItemContentsBinding>
    private val searchType by lazy {
        arguments?.get(ARG_TYPE) as? Tabs ?: error("SearchType is Null")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm = ViewModelProvider(this@ContentsFragment, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ContentsViewModel(
                    searchType,
                    NaverRepositoryImpl.getInstance(
                        NaverRemoteDataSourceImpl,
                        NaverLocalDataSourceImpl.getInstance(
                            SearchHistoryDatabase.getInstance(requireContext()).taskDao()
                        )
                    )
                ) as T
            }
        })[ContentsViewModel::class.java]

        binding.vm = vm
        binding.lifecycleOwner = this


        searchResultAdapter =
            SearchResultRecyclerAdapter(searchType, R.layout.item_contents, BR.item)
        binding.searchResultRecyclerView.adapter = searchResultAdapter

        vm.setObserves()
        updateSearchHistoryItems()

    }

    private fun ContentsViewModel.setObserves() {
        searchResultList.observe(viewLifecycleOwner, Observer {
            it?.let { updateItems(it) } ?: failToSearch()
        })

        errorQueryBlank.observe(viewLifecycleOwner, Observer {
            blankSearchQuery()
        })

        errorFailSearch.observe(viewLifecycleOwner, Observer {
            failToSearch()
        })
    }

    private fun updateSearchHistoryItems() {
        vm.getLastSearchHistory()
    }

    private fun updateItems(resultList: List<SearchResultShow.Item>) {
        searchResultAdapter.updateItems(resultList)
    }

    private fun failToSearch() {
        searchResultAdapter.clearItems()
        Toast.makeText(context, getString(R.string.err_search), Toast.LENGTH_SHORT).show()
    }

    private fun blankSearchQuery() {
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
