package com.jay.architecturestudy.ui.kin

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Kin
import com.jay.architecturestudy.model.ResponseNaverQuery
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragemnt_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KinFragment() : BaseFragment(R.layout.fragemnt_kin) {

    private lateinit var kinAdapter: KinAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            kinAdapter = KinAdapter()
            recycler_view.run {
                adapter = kinAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun search(keyword: String) {
        Api.getKin(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Kin>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Kin>>, t: Throwable) {
                    Log.e("Kin", "error=${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Kin>>,
                    response: Response<ResponseNaverQuery<Kin>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        kinAdapter.setData(body.items)
                    }
                }

            })
    }
}