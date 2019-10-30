package com.example.androidstudy.ui.blog

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudy.R.layout
import com.example.androidstudy.ui.base.BaseFragment
import com.ironelder.androidarchitecture.view.AdapterBlog
import kotlinx.android.synthetic.main.layout_search_view.*

class BlogFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_blog, container, false)
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
                apiFetchData(searchEditText.text.toString(),"tttt")
            }
        }

//        searchButton.setOnEditorActionListener { textView, id, keyEvent ->
//            when (id) {
//                EditorInfo.IME_ACTION_SEARCH -> {
//                    Log.d("TEST1234", "Search : ${textView.text.toString()}")
//                    if(!TextUtils.isEmpty(textView.text.toString())){
//                        apiFetchData(textView.text.toString())
//                    }
//                }
//                else -> false
//            }
//
//            true
//        }

        resultRecyclerView.adapter = AdapterBlog(context, arrayListOf(), "blog")
        resultRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        resultRecyclerView.setHasFixedSize(true)
    }


}