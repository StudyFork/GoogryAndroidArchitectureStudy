package com.example.architecturestudy.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.ImageData
import com.example.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.example.architecturestudy.network.Api
import com.example.architecturestudy.network.ApiClient
import kotlinx.android.synthetic.main.fragment_image.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : Fragment() {

    val restClient: Api = ApiClient.getRetrofitService(Api::class.java)

    private lateinit var imageAdapter: ImageAdapter

    private val naverSearchRepository by lazy { NaverSearchRepositoryImpl() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageAdapter = ImageAdapter()

        recycleview.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                searchImageList(edit)
            }
        }
    }

    private fun searchImageList(keyword : String) {
        naverSearchRepository.getImage(
            keyword = keyword,
            success = { responsImage -> imageAdapter.update(responsImage.items) },
            fail = { e -> error(message = e.toString()) }
        )
//        restClient.requestImage(keyword).enqueue(object : Callback<ImageData> {
//            override fun onFailure(call: Call<ImageData>, t: Throwable) {
//                error(message = t.toString())
//            }
//
//            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
//                if(response.isSuccessful) {
//                    response.body()?.items?.let {
//                        imageListAdapter()
//                        imageAdapter.update(it)
//                    }
//                }
//            }
//        })
    }

    private fun imageListAdapter() {
        recycleview.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
    }
}