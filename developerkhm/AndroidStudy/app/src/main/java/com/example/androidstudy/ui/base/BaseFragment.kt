package com.example.androidstudy.ui.base

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidstudy.model.repository.NaverDataRepositoryImpl
import com.ironelder.androidarchitecture.view.AdapterSearch
import kotlinx.android.synthetic.main.layout_search_view.*


open class BaseFragment(var layoutId: Int) : Fragment() {

    protected val typeArray = arrayOf("blog", "news", "movie", "book")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }


    protected fun search(str: String, typeStr: String) {
        //TODO 코틀린에 null 및 empty 체크 좋은거 많던데 나중에 수정~
        if (!TextUtils.isEmpty(str)) {

            NaverDataRepositoryImpl.getNaverSearchData(
                typeStr,
                searchEditText.text.toString(),
                { result ->
                    (resultRecyclerView?.adapter as AdapterSearch).setItemList(result.items)
                },
                { errMsg ->
                    Toast.makeText(context, errMsg, Toast.LENGTH_SHORT)
                })
        }
    }


    protected fun hideKeybaord(v: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.applicationWindowToken, 0)
    }
}
