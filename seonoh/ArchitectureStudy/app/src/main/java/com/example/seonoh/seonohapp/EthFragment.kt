package com.example.seonoh.seonohapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.network.CurrentPriceInfoRequest
import kotlinx.android.synthetic.main.eth_fragment.view.*

class EthFragment : Fragment(), CurrentPriceInfoRequest.ResultListener {

    lateinit var mView: View
    lateinit var mData: ArrayList<CurrentPriceInfoModel>
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mAdapter: CoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.eth_fragment, container, false)
        Log.e("onCreateView", "onCreateView")

        requestData()

        return mView
    }

    fun initView(data: ArrayList<CurrentPriceInfoModel>) {
        mAdapter = CoinAdapter(data, ETH_TYPE)
        mLinearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mView.ethRecyclerView.apply {
            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }

    fun requestData() {
        CurrentPriceInfoRequest().send(this, MainActivity.ethMarketData)
    }

    override fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        mData = result
        Log.e("getCurrentInfoSuccess","getCurrentInfoSuccess ETH : $result")
        initView(mData)
    }

    override fun getCurrentInfoFailed(code: String) {

    }


    companion object {
        fun newInstance(): EthFragment {
            val fragment = EthFragment()
            return fragment
        }
    }
}
