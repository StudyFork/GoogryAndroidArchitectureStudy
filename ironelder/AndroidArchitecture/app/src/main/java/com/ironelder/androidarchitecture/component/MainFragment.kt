package com.ironelder.androidarchitecture.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.utils.RetrofitForNaver
import com.ironelder.androidarchitecture.view.CustomListViewAdapter
import kotlinx.android.synthetic.main.layout_search_listview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment {
    private val mType: String

    constructor(type: String?) {
        mType = type ?: "blog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        v.searchButton.setOnClickListener {
            hideKeybaord(it)
            searchAction(v.searchEditText.text.toString())
        }

        v.searchEditText.setOnEditorActionListener { editView, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    hideKeybaord(editView)
                    searchAction(editView.text.toString())
                }
                else -> false
            }
            true
        }

        v.resultListView.adapter = CustomListViewAdapter(context, arrayListOf())
        v.resultListView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        v.resultListView.setHasFixedSize(true)
        v.resultListView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
        )
        return v
    }

    private fun hideKeybaord(v: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.applicationWindowToken, 0)
    }

    private fun searchAction(searchString: String) {
        val retrofitService = RetrofitForNaver.getSearchForNaver()
        println("mType = $mType")
        println("searchString = $searchString")
        var result = retrofitService.requestSearchForNaver(mType, searchString)
        result.enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                println("onFailure = ${t.message}")
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                println("onResponse")
                var resultList = response.body()
                (view?.resultListView?.adapter as CustomListViewAdapter).setItemList(resultList?.items)
                (view?.resultListView?.adapter as CustomListViewAdapter).notifyDataSetChanged()
            }
        })
    }

}