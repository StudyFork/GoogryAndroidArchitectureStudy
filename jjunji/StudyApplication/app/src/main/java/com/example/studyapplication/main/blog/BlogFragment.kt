package com.example.studyapplication.main.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.main.blog.adapter.BlogAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.Conn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.BlogList
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : Fragment() {
    lateinit var blogAdapter: BlogAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSearch.setOnClickListener(btnSearchClickListener())
        blogAdapter = BlogAdapter()
        recyclerView.adapter = blogAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val blogTitle = etQuery.text.toString()
            requestSearchBlog(blogTitle)
        }
    }

    // 영화 검색 요청
    private fun requestSearchBlog(title : String) {
        Remote.get(ApiClient.getService().getBlogList(title), object : Conn {
            override fun <T> success(result: T) {
                val blogList : BlogList? = result as BlogList
                blogList?.let {
                    blogAdapter.addItem(blogList.arrBlogInfo)
                }
            }

            override fun failed() {
            }
        })
    }

    companion object {
        fun newInstance() : BlogFragment {
            return BlogFragment()
        }
    }
}