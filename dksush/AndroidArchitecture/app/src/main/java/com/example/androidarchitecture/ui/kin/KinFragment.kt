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
            kinAdapter = KinAdapter(it)
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
                requestKinList(edit_text.text.toString())
            }
        }
    }

    private fun requestKinList(text: String) {
        NetworkUtil.apiService.getKinList(text, 1, 10)
            .enqueue(object : Callback<KinData> {
                override fun onFailure(call: Call<KinData>, t: Throwable) {
                    Log.v("dksush_error", t.toString())
                }

                override fun onResponse(call: Call<KinData>, response: Response<KinData>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.kins ?: return
                        kinAdapter.setData(body)
                    }
                }

            })


    }


}
