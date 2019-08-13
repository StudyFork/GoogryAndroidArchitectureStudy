package com.example.seonoh.seonohapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.network.CurrentPriceInfoRequest
import kotlinx.android.synthetic.main.btc_fragment.view.*
import kotlinx.android.synthetic.main.eth_fragment.view.*

class BtcFragmnet : BaseFragment(), CurrentPriceInfoRequest.ResultListener {

    lateinit var mView: View
    lateinit var mData: ArrayList<CurrentPriceInfoModel>
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mAdapter: CoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.btc_fragment, container, false)
        Log.e("onCreateView", "onCreateView")
        showLoading()

        requestData()

        return mView
    }

    fun initView(data: ArrayList<CurrentPriceInfoModel>) {
        mAdapter = CoinAdapter(data, BTC_TYPE)
        mLinearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mView.btcRecyclerView.apply {
            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }

    fun requestData() {
        CurrentPriceInfoRequest().send(this, MainActivity.btcMarketData)
    }

    override fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        hideLoading()
        mData = result
        initView(mData)

    }

    override fun getCurrentInfoFailed(code: String) {
        hideLoading()
    }


    companion object {
        fun newInstance(): BtcFragmnet {
            val fragment = BtcFragmnet()
            return fragment
        }
    }
}
