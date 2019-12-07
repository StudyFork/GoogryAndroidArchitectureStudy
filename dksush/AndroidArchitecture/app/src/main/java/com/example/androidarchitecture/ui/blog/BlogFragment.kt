package com.example.androidarchitecture.ui.blog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.BlogData
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : Fragment() {

    private lateinit var blogAdapter: BlogAdapter

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

        activity?.let {
            blogAdapter = BlogAdapter()
                .also {
                    recycle.adapter = it
                    recycle.addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
        }


        btn_search.setOnClickListener {
            requestBlogList(edit_text.text.toString())
        }
    }


    private fun requestBlogList(text: String) {
        NetworkUtil.apiService.getBlogList(text, 1, 10)
            .enqueue(object : retrofit2.Callback<BlogData> {
                override fun onFailure(call: Call<BlogData>, t: Throwable) {
                }

                override fun onResponse(call: Call<BlogData>, response: Response<BlogData>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.blogs ?: return
                        blogAdapter.setData(body)

                    }
                }

            })
    }


}
