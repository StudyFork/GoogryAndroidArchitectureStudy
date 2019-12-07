package com.example.androidarchitecture.ui.image


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.ImageData
import com.example.androidarchitecture.models.NaverQueryResponse
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

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



        activity?.let {
            imageAdapter = ImageAdapter()
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
            if (edit_text != null) {
                requestImageList(edit_text.text.toString())
            }
        }
    }


    private fun requestImageList(text: String) {
        NetworkUtil.apiService.getImageList(text, 1, 10)
            .enqueue(object : Callback<NaverQueryResponse<ImageData>> {
                override fun onFailure(call: Call<NaverQueryResponse<ImageData>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<NaverQueryResponse<ImageData>>, response: Response<NaverQueryResponse<ImageData>>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.items ?: return
                        imageAdapter.setData(body)
                    }
                }
            })


    }

}
