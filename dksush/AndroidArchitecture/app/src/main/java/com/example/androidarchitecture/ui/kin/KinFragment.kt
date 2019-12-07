package com.example.androidarchitecture.ui.kin


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
import com.example.androidarchitecture.models.KinData
import com.example.androidarchitecture.models.NaverQueryResponse
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class KinFragment : Fragment() {

    private lateinit var kinAdapter: KinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        activity?.let {
            kinAdapter = KinAdapter()
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
                requestKinList(edit_text.text.toString())
            }
        }
    }

    private fun requestKinList(text: String) {
        NetworkUtil.apiService.getKinList(text, 1, 10)
            .enqueue(object :Callback<NaverQueryResponse<KinData>>{
                override fun onFailure(call: Call<NaverQueryResponse<KinData>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<NaverQueryResponse<KinData>>, response: Response<NaverQueryResponse<KinData>>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.items ?: return
                        kinAdapter.setData(body)
                    }
                }
            })


    }


}
