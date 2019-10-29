package com.example.androidstudy.ui.blog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidstudy.R
import com.example.androidstudy.R.layout
import com.example.androidstudy.api.RetrofitBuilder
import com.example.androidstudy.api.data.TotalModel
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

    private fun setLayout(){
        searchButton.setOnEditorActionListener { textView, id, keyEvent ->
            when (id) {
                EditorInfo.IME_ACTION_SEARCH -> {
//
                }
                else -> false
            }
            true
        }


    }

    private fun apiFetchData() {
        val result = RetrofitBuilder.instance().requestSearchForNaver("blog", "치킨")
        result.enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT)
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                var resultList = response.body()
                Log.d("TEST1234", "RESUTL : ${resultList.toString()}")
            }
        })
    }
}