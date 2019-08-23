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

const val MARKET = "market"
class CoinFragment : Fragment(), CoinRequest.BaseResult<ArrayList<CurrentPriceInfoModel>> {

    lateinit var coinFragmentView: View
    lateinit var mData: ArrayList<UseCoinModel>
    lateinit var mAdapter: CoinAdapter
    private var marketName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coinFragmentView = inflater.inflate(R.layout.coin_fragment, container, false)

        if (arguments != null) {
            marketName = arguments!!.getString(MARKET)
        }

        requestData()

        return coinFragmentView
    }

    private fun setData(data: ArrayList<UseCoinModel>) {
        mAdapter.addCoinData(data)
    }

    private fun initView() {
        mAdapter = CoinAdapter()
        coinFragmentView.krwRecyclerView.apply {
            adapter = mAdapter
        }
    }

    private fun requestData() {
        CoinRequest(RetrofitCreator.coinApi).sendCurrentPriceInfo(this, marketName)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

    }

    override fun getNetworkSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        mData = ArrayList()
        var marketType = ""

        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        if (result.size != 0) {
            marketType = result[0].market.substringBefore("-")
        }

        val data  = result.map {
                UseCoinModel(
                    CalculateUtils.setMarketName(it.market),
                    CalculateUtils.filterTrade(it.tradePrice),
                    it.signedChangeRate,
                    CalculateUtils.setTradeAmount(marketType, it.accTradePrice24h, context!!)
                )

        } as ArrayList<UseCoinModel>



        setData(data)
    }

    override fun getNetworkFailed(code: String) {
    }

    companion object {
        fun newInstance(data: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString(MARKET, data)
            fragment.arguments = bundle
            return fragment
        }
    }
}
