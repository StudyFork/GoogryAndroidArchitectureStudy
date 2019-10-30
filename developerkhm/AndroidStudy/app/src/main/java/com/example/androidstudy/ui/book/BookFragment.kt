package com.example.androidstudy.ui.book

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudy.R.layout
import com.example.androidstudy.ui.base.BaseFragment
import com.ironelder.androidarchitecture.view.AdapterBlog
import kotlinx.android.synthetic.main.layout_search_view.*

class BookFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View =
            inflater.inflate(layout.fragment_book, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setLayout()
    }

    private fun setLayout() {

        searchButton.setOnClickListener {
            if (!TextUtils.isEmpty(searchEditText.text.toString())) {
                apiFetchData(searchEditText.text.toString(), typeArray[3], { response ->
                    var resultList = response.body()
                    Log.d("TEST1234", "RESUTL : ${resultList.toString()}")
                    (resultRecyclerView?.adapter as AdapterBlog).setItemList(resultList?.items)
                }, {

                })
            }
        }

        resultRecyclerView.adapter = AdapterBlog(context, arrayListOf(), "blog")
        resultRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }
}