package com.example.androidarchitecture.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.Api
import com.example.androidarchitecture.apis.Api.Companion.base_url
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.BlogData
import com.example.androidarchitecture.models.ResponseBlog
import com.example.androidarchitecture.ui.blog.blogAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_search.setOnClickListener {
            if (input_text != null) {
                requestBlogList(edit_text.text.toString())
            }
        }
    }

    private fun requestBlogList(text: String) {
        val retrofit = NetworkUtil.getRetrofit(base_url, GsonConverterFactory.create())
        val api = retrofit.create(Api::class.java)
        val blogInfo = api.getBloglist(text, 1, 10)
        blogInfo.enqueue(object : retrofit2.Callback<BlogData> {
            override fun onFailure(call: Call<BlogData>, t: Throwable) {
            }

            override fun onResponse(call: Call<BlogData>, response: Response<BlogData>) {
                if (response.isSuccessful){
                    response.body()?.blogs?.let { setList(it) }
                }
            }


        })

    }

    private fun setList(blog: List<ResponseBlog>) {
        recycle.adapter =
            blogAdapter(blog)
        recycle.layoutManager = LinearLayoutManager(activity)
        recycle.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

    }
}
