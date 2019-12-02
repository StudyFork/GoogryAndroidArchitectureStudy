package com.jay.architecturestudy.ui.kin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.ResponseKin
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragemnt_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KinFragment : BaseFragment() {
    private lateinit var kinAdapter: KinAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragemnt_kin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { activity ->
            kinAdapter = KinAdapter(activity)
                .also {
                    recycler_view.run {
                        adapter = it
                        layoutManager = LinearLayoutManager(activity)
                        addItemDecoration(
                            DividerItemDecoration(
                                activity,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                }
        }
    }

    override fun search(keyword: String) {
        Api.getKin(keyword)
            .enqueue(object : Callback<ResponseKin> {
                override fun onFailure(call: Call<ResponseKin>, t: Throwable) {
                    Log.e("Kin", "error=${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseKin>,
                    response: Response<ResponseKin>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        kinAdapter.setData(body.kins)
                    }
                }

            })
    }
}