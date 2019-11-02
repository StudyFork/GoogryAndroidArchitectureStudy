package com.ironelder.androidarchitecture.component

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.common.BLOG
import com.ironelder.androidarchitecture.common.TYPE_KEY
import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.view.CustomListViewAdapter
import kotlinx.android.synthetic.main.layout_search_listview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : BaseFragment() {
    private val mType: String? by lazy {
        arguments?.getString(TYPE_KEY)
    }

    override fun doCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun doViewCreated(view: View, savedInstanceState: Bundle?) {
        with(rv_resultListView) {
            adapter = CustomListViewAdapter(mType ?: BLOG)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager(context).orientation

                )
            )
        }
    }

    override fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search)?.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchAction(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchAction(searchString: String?) {
        if (searchString.isNullOrEmpty()) {
            Toast.makeText(context, getString(R.string.msg_empty_search_string), Toast.LENGTH_SHORT)
                .show()
            return
        }
        val retrofitService = RetrofitForNaver.searchApi
        val result = retrofitService.requestSearchForNaver(mType ?: BLOG, searchString)
        result.enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                val resultList = response.body()
                (rv_resultListView?.adapter as CustomListViewAdapter).setItemList(resultList?.items)
            }
        })
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