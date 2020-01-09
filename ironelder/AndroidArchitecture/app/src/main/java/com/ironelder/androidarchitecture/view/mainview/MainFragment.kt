package com.ironelder.androidarchitecture.view.mainview

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
    }

    override fun doActivityCreated(savedInstanceState: Bundle?) {
        binding.mainViewModel =
            ViewModelProviders.of(this@MainFragment)[MainViewModel::class.java].apply {
                notifyErrorMessage.observe(viewLifecycleOwner, Observer {
                    if (!it.isNullOrEmpty()) {
                        Toast.makeText(context, it, Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
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
    }

    override fun doLoadFromDatabase() {
        binding.mainViewModel?.apply {
            getSearchResultToRoom(
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
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.mainViewModel?.apply {
                    searchQuery.value = query
                    searchWithAdapter(
                        mType ?: BLOG,
                        SearchResultDatabase.getInstance(
                            context?.applicationContext
                                ?: (activity as? Context)?.applicationContext!!
                        )
                    )
                }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView.setOnSearchClickListener {
            if (!binding.mainViewModel?.searchQuery?.value.isNullOrEmpty()) {
                searchView.setQuery(binding.mainViewModel?.searchQuery?.value, false)
            }
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