package com.example.androidarchitecture.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.apis.Api
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.ImageData
import com.example.androidarchitecture.models.ResponseImage
import com.example.androidarchitecture.ui.image.imageAdpater
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    private var mArrData: ArrayList<ResponseImage>? = null


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
            if (edit_text != null) {
                requestImageList(edit_text.text.toString())
            }
        }
    }


    private fun requestImageList(text: String) {
        val retrofit = NetworkUtil.getRetrofit(Api.base_url, GsonConverterFactory.create())
        val api = retrofit.create(Api::class.java)
        val imageInfo = api.getImagelist(text, 1, 10)
        imageInfo.enqueue(object : Callback<ImageData> {
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                Log.v("dksush_error", t.toString())
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if (response.isSuccessful){
                    response.body()?.images?.let {
                        setList(it)
                    }
                }

            }
        })

    }

    private fun setList(image: List<ResponseImage>) {
        recycle.adapter =
            imageAdpater(image)
        recycle.layoutManager = LinearLayoutManager(activity)

    }
}
