package com.example.androidstudy.ui.base

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidstudy.R
import com.example.androidstudy.api.RetrofitBuilder
import com.example.androidstudy.api.data.TotalModel
import com.example.androidstudy.ui.blog.BlogFragment
import com.example.androidstudy.ui.book.BookFragment
import com.example.androidstudy.ui.movie.MovieFragment
import com.example.androidstudy.ui.news.NewsFragment
import com.ironelder.androidarchitecture.view.AdapterSearch
import kotlinx.android.synthetic.main.layout_search_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


open class BaseFragment : Fragment() {

    protected val typeArray = arrayOf("blog", "news", "movie", "book")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if(this is BlogFragment){
            return inflater.inflate(R.layout.fragment_blog, container, false)
        }else if(this is NewsFragment){
            return inflater.inflate(R.layout.fragment_news, container, false)
        }else if(this is MovieFragment){
            return inflater.inflate(R.layout.fragment_movie, container, false)
        }else if(this is BookFragment){
            return inflater.inflate(R.layout.fragment_book, container, false)
        }

        return null
    }

    protected fun search(str : String, typeStr : String){
        if (!TextUtils.isEmpty(str)) {
            apiFetchData(searchEditText.text.toString(), typeStr, { response ->
                var resultList = response.body()
                Log.d("TEST1234", "RESUTL : ${resultList.toString()}")
                (resultRecyclerView?.adapter as AdapterSearch).setItemList(resultList!!.items)
            }, {

            })
        }
    }

    protected fun apiFetchData(searchStr: String, type : String, success : (Response<TotalModel>) -> Unit, fail : () -> Unit) {
        val result = RetrofitBuilder.instance().requestSearchForNaver(type, searchStr)
        result.enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT)
                fail()
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                success(response)
            }
        })
    }

    protected fun hideKeybaord(v: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.applicationWindowToken, 0)
    }
}
