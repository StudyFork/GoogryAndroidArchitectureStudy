package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.network.CoinRequest
import com.example.seonoh.seonohapp.network.RetrofitCreator
import kotlinx.android.synthetic.main.coin_fragment.view.*


class CoinFragment : Fragment(),CoinRequest.CurrentPriceInfoResultListener{

    lateinit var mView : View
    lateinit var mData : ArrayList<CurrentPriceInfoModel>
    lateinit var mAdapter : CoinAdapter
    var marketName = ""
    var marketType = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.coin_fragment, container, false)

        if(arguments != null){
            marketName = arguments!!.getString("market")
        }
        requestData()

        return mView
    }

    fun initView( data: ArrayList<CurrentPriceInfoModel>){
        mAdapter = CoinAdapter()
        mView.krwRecyclerView.apply {
            adapter = mAdapter
            mAdapter.addCoinData(data)
        }
    }

    fun requestData(){
        CoinRequest().currentPriceInfoSend(RetrofitCreator.coinApi,this, marketName)
    }

    override fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        mData = result
        initView(mData)
    }

    override fun getCurrentInfoFailed(code: String) {
    }

    companion object{
        fun newInstance(data : String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString("market",data)
            fragment.arguments = bundle
            return fragment
        }
    }
}
