package com.example.androidarchitecture.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecture.R
import com.example.androidarchitecture.WebviewActivirt
import com.example.androidarchitecture.adapters.ImageAdapter
import com.example.androidarchitecture.apis.Api
import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.models.ImageData
import com.example.androidarchitecture.models.Image_item
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    private var mArrData: ArrayList<Image_item>? = null


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
            if (input_text != null) {
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
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if (response.code() == 200) {
                    mArrData = ArrayList()
                    response.body()?.images?.let {
                        for (info in it) {
                            mArrData?.add(info)
                        }
                        mArrData?.let { setList(it) }
                    }
                }
            }
        })

    }

    private fun setList(image: ArrayList<Image_item>) {
        recycle.adapter =
            ImageAdapter(image, activity!!, object : ImageAdapter.OnItemClickListener {
                override fun onItemClick(link: String) {
                    val intent = Intent(context, WebviewActivirt::class.java)
                    intent.putExtra("link", link)
                    context?.startActivity(intent)
                }
            })
        recycle.layoutManager = LinearLayoutManager(activity)

    }
}
