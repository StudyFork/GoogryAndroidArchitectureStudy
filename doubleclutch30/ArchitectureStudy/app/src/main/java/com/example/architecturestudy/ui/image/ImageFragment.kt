package com.example.architecturestudy.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.model.ImageData
import com.example.architecturestudy.model.ImageItems
import com.example.architecturestudy.network.Api
import com.example.architecturestudy.network.ApiClient
import kotlinx.android.synthetic.main.fragment_image.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : Fragment() {

    val restClient: Api = ApiClient.getRetrofitService(Api::class.java)

    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                searchImageList(edit)
            }
        }
    }

    private fun searchImageList(keyword : String) {
        restClient.requestImage(keyword).enqueue(object : Callback<ImageData> {
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                error(message = t.toString())
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let { imageListAdapter(it) }
                }
            }
        })
    }

    private fun imageListAdapter(image : List<ImageItems>) {
        imageAdapter = ImageAdapter(image)
        recycleview.adapter = imageAdapter
        recycleview.layoutManager = LinearLayoutManager(activity)
        recycleview.addItemDecoration(
            DividerItemDecoration(
                activity, DividerItemDecoration.VERTICAL
            )
        )

        imageAdapter.upDate(image)
    }
}