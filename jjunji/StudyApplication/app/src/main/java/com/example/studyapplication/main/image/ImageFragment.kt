package com.example.studyapplication.main.image

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyapplication.R
import com.example.studyapplication.main.image.adapter.ImageAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.IConn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.ImageList
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_movie.view.*

class ImageFragment : Fragment() {
    lateinit var mContext : Context

    companion object {
        fun newInstance() : ImageFragment {
            return ImageFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_image, container, false)
        mContext = view.context

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener(btnSearchClickListener())
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val blogTitle = etQuery.text.toString()
            requestSearchBlog(blogTitle)
        }
    }

    // 이미지 검색 요청
    private fun requestSearchBlog(title : String) {
        Remote.get(ApiClient.getService().getImageList(title), object : IConn {
            override fun <T> success(result: T) {
                val imageList : ImageList? = result as ImageList
                imageList?.let {
                    setAdapter(it)
                }
            }

            override fun failed() {
            }
        })
    }

    // recyclerView 세팅
    private fun setAdapter(response : ImageList) {
        recyclerView.adapter = ImageAdapter(response.arrImageInfo)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}