package com.ironelder.androidarchitecture.view.mainview

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.Observable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.common.BLOG
import com.ironelder.androidarchitecture.common.TYPE_KEY
import com.ironelder.androidarchitecture.component.CustomListViewAdapter
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import com.ironelder.androidarchitecture.databinding.FragmentMainBinding
import com.ironelder.androidarchitecture.view.baseview.BaseFragment


class MainFragment :
    BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mType: String? by lazy {
        arguments?.getString(TYPE_KEY)
    }

    override fun doViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mainViewModel = MainViewModel()
        with(binding.searchLayout.rvResultListView) {
            adapter =
                CustomListViewAdapter()
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager(context).orientation
                )
            )
        }
        binding.mainViewModel.let {
            it?.searchQuery?.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    it.searchWithAdapter(
                        mType ?: BLOG,
                        it.searchQuery.get(),
                        SearchResultDatabase.getInstance(
                            context?.applicationContext ?: (activity as Context).applicationContext
                        )
                    )
                }
            })
            it?.notifyErrorMessage?.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (!it.notifyErrorMessage.get().isNullOrEmpty()) {
                        Toast.makeText(context, it.notifyErrorMessage.get(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

            })
        }
    }

    override fun doLoadFromDatabase() {
        binding.mainViewModel.let {
            it?.getSearchResultToRoom(
                mType ?: BLOG,
                SearchResultDatabase.getInstance(
                    context?.applicationContext ?: (activity as Context).applicationContext
                )
            )
        }
    }

    override fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchView =
            SearchView((context as? MainActivity)?.supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search)?.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.mainViewModel.let {
                    it?.searchQuery?.set(query ?: "")
                }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView.setOnSearchClickListener {
            if (!binding.mainViewModel?.searchQuery?.get().isNullOrEmpty()) {
                searchView.setQuery(binding.mainViewModel?.searchQuery?.get(), false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mainViewModel.let {
            it?.clearDisposable()
        }
    }

    companion object {
        fun newInstance(type: String?): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putString(TYPE_KEY, type)
                }
            }
        }
    }

}