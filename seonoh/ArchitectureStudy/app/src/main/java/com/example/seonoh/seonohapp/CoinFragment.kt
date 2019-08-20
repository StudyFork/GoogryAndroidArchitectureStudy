package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel
import com.example.seonoh.seonohapp.network.CoinRequest
import com.example.seonoh.seonohapp.network.RetrofitCreator
import com.example.seonoh.seonohapp.util.CalculateUtils
import kotlinx.android.synthetic.main.coin_fragment.view.*


class CoinFragment : Fragment(), CoinRequest.CurrentPriceInfoResultListener {

    lateinit var mView: View
    lateinit var mData: ArrayList<UseCoinModel>
    lateinit var mAdapter: CoinAdapter
    var marketName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.coin_fragment, container, false)

        if (arguments != null) {
            marketName = arguments!!.getString("market")
        }
        requestData()

        return mView
    }

    fun setData(data: ArrayList<UseCoinModel>){
        mAdapter.addCoinData(data)
    }

    fun initView() {
        mAdapter = CoinAdapter()
        mView.krwRecyclerView.apply {
            adapter = mAdapter
        }
    }

    fun requestData() {
        CoinRequest(RetrofitCreator.coinApi).currentPriceInfoSend( this, marketName)
    }

    override fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        mData = ArrayList()

        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        for (i in 0 until result.size) {
            val model = UseCoinModel(
                CalculateUtils.setMarketName(result[i].market),
                CalculateUtils.filterTrade(result[i].tradePrice),
                result[i].signedChangeRate.toString(),
                CalculateUtils.setTradeAmount(result[i].accTradePrice_24h)
            )
            mData.add(model)
        }

        initView()
        setData(mData)
    }

    override fun getCurrentInfoFailed(code: String) {
    }

    companion object {
        fun newInstance(data: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString("market", data)
            fragment.arguments = bundle
            return fragment
        }
    }
}
