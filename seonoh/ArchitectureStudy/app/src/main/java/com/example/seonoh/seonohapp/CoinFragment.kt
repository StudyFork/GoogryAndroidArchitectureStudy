package com.example.seonoh.seonohapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.network.CurrentPriceInfoRequest
import kotlinx.android.synthetic.main.coin_fragment.view.*


class CoinFragment : BaseFragment(),CurrentPriceInfoRequest.ResultListener{

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
            marketType = arguments!!.getInt("type")
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
        CurrentPriceInfoRequest().send(this, marketName)
    }

    override fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        mData = result
        initView(mData)
    }

    override fun getCurrentInfoFailed(code: String) {
    }


    companion object{
        fun newInstance(type : Int,data : String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString("market",data)
            bundle.putInt("type",type)
            fragment.arguments = bundle
            return fragment
        }
    }
}
