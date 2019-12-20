package com.example.androidarchitecture.ui.blog


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.data.repository.NaverRepo
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.NaverQueryResponse
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : Fragment() {

    private lateinit var blogAdapter: BlogAdapter
    private val naverRepo = NaverRepo()

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

        naverRepo.getBlog(text,1,10,
            success = {
                blogAdapter.setData(it)
            }, fail = {
                Log.v("dksush", it.toString())
            })

    }


}
