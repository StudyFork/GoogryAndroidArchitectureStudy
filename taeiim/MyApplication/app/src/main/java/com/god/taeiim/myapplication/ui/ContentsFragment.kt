package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
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

    private val vm: ContentsViewModel by lazy {
        ContentsViewModel(
            NaverRepositoryImpl.getInstance(
                NaverRemoteDataSourceImpl,
                NaverLocalDataSourceImpl.getInstance(
                    SearchHistoryDatabase.getInstance(requireActivity().applicationContext).taskDao()
                )
            )
        )
    }

    lateinit var searchResultAdapter: SearchResultRecyclerAdapter<SearchResultShow.Item, ItemContentsBinding>
    private lateinit var searchType: Tabs

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.vm = vm

        arguments?.getSerializable(ARG_TYPE)?.let {
            searchType = it as Tabs
            vm.searchType = searchType
        }

        searchResultAdapter =
            SearchResultRecyclerAdapter(searchType, R.layout.item_contents, BR.item)
        binding.searchResultRecyclerView.adapter = searchResultAdapter

        updateSearchHistoryItems()
        addObserveProperty()

    }

    private fun addObserveProperty() {
        vm.searchResultList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                vm.searchResultList.get()?.let {
                    updateItems(it)
                } ?: failToSearch()
            }
        })

        vm.errorQueryBlank.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                blankSearchQuery()
            }
        })

        vm.errorFailSearch.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                failToSearch()
            }
        })
    }

    private fun updateSearchHistoryItems() {
        vm.getLastSearchHistory(searchType)
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
