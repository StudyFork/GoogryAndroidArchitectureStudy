package com.jay.architecturestudy.ui.blog

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Blog
import com.jay.architecturestudy.model.ResponseNaverQuery
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragemnt_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogFragment(layoutId: Int = R.layout.fragemnt_blog) : BaseFragment(layoutId) {

    private lateinit var blogAdapter: BlogAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            blogAdapter = BlogAdapter()
                .also {
                    recycler_view.run {
                        adapter = it
                        layoutManager = LinearLayoutManager(activity)
                        addItemDecoration(
                            DividerItemDecoration(
                                activity,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun search(keyword: String) {
        Api.getBlog(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Blog>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Blog>>, t: Throwable) {
                    Log.e("Blog", "error=${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Blog>>,
                    response: Response<ResponseNaverQuery<Blog>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        blogAdapter.setData(body.items)
                    }
                }

            })
    }
}