package com.god.taeiim.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.base.BaseFragment
import com.god.taeiim.myapplication.data.source.NaverRepositoryImpl
import com.god.taeiim.myapplication.data.source.local.NaverLocalDataSourceImpl
import com.god.taeiim.myapplication.data.source.local.SearchHistoryDatabase
import com.god.taeiim.myapplication.data.source.remote.NaverRemoteDataSourceImpl
import kotlinx.android.synthetic.main.fragment_main.*

class ContentsFragment : BaseFragment(), ContentsContract.View {

    override lateinit var presenter: ContentsContract.Presenter
    private lateinit var adapter: SearchResultRecyclerAdapter
    private lateinit var searchType: Tabs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = ContentsPresenter(
            NaverRepositoryImpl.getInstance(
                NaverRemoteDataSourceImpl,
                NaverLocalDataSourceImpl.getInstance(
                    SearchHistoryDatabase.getInstance(requireActivity().applicationContext).taskDao()
                )
            ), this
        )

        arguments?.getSerializable(ARG_TYPE)?.let {
            searchType = it as Tabs
        }

        adapter = SearchResultRecyclerAdapter(searchType)
        searchResultRecyclerView.adapter = this@ContentsFragment.adapter

        searchBtn.setOnClickListener {
            presenter.searchContents(
                searchType.name,
                searchEditTv.text.toString()
            )
        }
    }

    override fun updateItems(resultList: SearchResult) {
        adapter.setItems(resultList.items)
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
