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
import com.example.androidarchitecture.models.KinData
import com.example.androidarchitecture.models.ResponseKin
import com.example.androidarchitecture.ui.kin.kinAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class KinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_search.setOnClickListener {
            if (edit_text != null) {
                requestKinList(edit_text.text.toString())
            }
        }
    }

    private fun requestKinList(text: String) {
        val retrofit = NetworkUtil.getRetrofit(Api.base_url, GsonConverterFactory.create())
        val api = retrofit.create(Api::class.java)
        val kinInfo = api.getKinlist(text, 1, 10)
        kinInfo.enqueue(object : Callback<KinData> {
            override fun onFailure(call: Call<KinData>, t: Throwable) {
                Log.v("dksush_error", t.toString())
            }

            override fun onResponse(call: Call<KinData>, response: Response<KinData>) {
                if (response.isSuccessful){
                    response.body()?.kins?.let {
                        setList(it)
                    }
                }

            }
        })

    }

    private fun setList(kin: List<ResponseKin>) {
        recycle.adapter =
            kinAdapter(kin)
        recycle.layoutManager = LinearLayoutManager(activity)

    }


}
