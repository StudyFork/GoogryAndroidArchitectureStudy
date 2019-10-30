package com.example.androidstudy.ui.blog

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudy.R.layout
import com.example.androidstudy.api.RetrofitBuilder
import com.example.androidstudy.api.data.TotalModel
import com.ironelder.androidarchitecture.view.AdapterBlog
import kotlinx.android.synthetic.main.layout_search_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogFragment : Fragment() {
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
                apiFetchData(searchEditText.text.toString())
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


    private fun apiFetchData(str: String) {
        val result = RetrofitBuilder.instance().requestSearchForNaver("blog", str)
        result.enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT)
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                var resultList = response.body()
                Log.d("TEST1234", "RESUTL : ${resultList.toString()}")
                (resultRecyclerView?.adapter as AdapterBlog).setItemList(resultList?.items)
            }
        })
    }
}