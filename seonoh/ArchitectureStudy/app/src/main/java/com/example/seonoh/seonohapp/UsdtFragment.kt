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
import kotlinx.android.synthetic.main.usdt_fragment.view.*

class UsdtFragment :BaseFragment(), CurrentPriceInfoRequest.ResultListener {

    lateinit var mView: View
    lateinit var mData: ArrayList<CurrentPriceInfoModel>
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mAdapter: CoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.usdt_fragment, container, false)
        Log.e("onCreateView", "onCreateView")
        showLoading()
        requestData()

        return mView
    }

    fun initView(data: ArrayList<CurrentPriceInfoModel>) {
        mAdapter = CoinAdapter(data, USDT_TYPE)
        mLinearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mView.usdtRecyclerView.apply {
            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }

    fun requestData() {
        CurrentPriceInfoRequest().send(this, MainActivity.usdtMarketData)
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
        fun newInstance(): UsdtFragment {
            val fragment = UsdtFragment()
            return fragment
        }
    }
}
