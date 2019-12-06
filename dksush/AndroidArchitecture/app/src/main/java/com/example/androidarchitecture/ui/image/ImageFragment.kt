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
            imageAdapter = ImageAdapter(it)
                .also {
                    recycle.adapter = it
                    recycle.layoutManager = LinearLayoutManager(activity)
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
        NetworkUtil.getApiService().getImageList(text, 1, 10)
            .enqueue(object : Callback<ImageData> {
                override fun onFailure(call: Call<ImageData>, t: Throwable) {
                    Log.v("dksush_error", t.toString())
                }

                override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.images ?: return
                        imageAdapter.setData(body)
                    }
                }
            })


    }

}
