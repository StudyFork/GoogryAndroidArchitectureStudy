package com.example.architecturestudy.ui.kin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.model.KinData
import com.example.architecturestudy.model.KinItems
import com.example.architecturestudy.network.Api
import com.example.architecturestudy.network.ApiClient
import kotlinx.android.synthetic.main.fragment_kin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KinFragment : Fragment() {

    val restClient: Api = ApiClient.getRetrofitService(Api::class.java)

    private lateinit var kinAdapter: KinAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                searchKinList(edit)
            }
        }
    }

    private fun searchKinList(keyword : String) {
        restClient.requestKin(keyword).enqueue(object : Callback<KinData> {

            override fun onFailure(call: Call<KinData>, t: Throwable) {
                error(message = t.toString())
            }

            override fun onResponse(call: Call<KinData>, response: Response<KinData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let { kinListAdapter(it) }
                }
            }
        })
    }

    private fun kinListAdapter(kin : List<KinItems>) {
        kinAdapter =  KinAdapter(kin)
        recycleview.adapter = kinAdapter
        recycleview.layoutManager = LinearLayoutManager(activity)
        recycleview.addItemDecoration(
            DividerItemDecoration(
                activity, DividerItemDecoration.VERTICAL
            )
        )

        kinAdapter.upDate(kin)
    }
}
