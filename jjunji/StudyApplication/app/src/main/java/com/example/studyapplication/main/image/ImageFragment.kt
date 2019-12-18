package com.example.studyapplication.main.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.main.image.adapter.ImageAdapter
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.Conn
import com.example.studyapplication.network.Remote
import com.example.studyapplication.vo.ImageList
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment() {
    lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSearch.setOnClickListener(btnSearchClickListener())
        imageAdapter = ImageAdapter()
        recyclerView.adapter = imageAdapter
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
        Remote.get(ApiClient.getService().getImageList(title), object : Conn {
            override fun <T> success(result: T) {
                val imageList : ImageList? = result as ImageList
                imageList?.let {
                    imageAdapter.resetItem(imageList.arrImageInfo)
                }
            }

            override fun failed() {
            }
        })
    }

    companion object {
        fun newInstance() : ImageFragment {
            return ImageFragment()
        }
    }
}