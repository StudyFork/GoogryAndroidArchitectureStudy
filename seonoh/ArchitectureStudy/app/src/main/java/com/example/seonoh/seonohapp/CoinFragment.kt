package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel
import com.example.seonoh.seonohapp.network.CoinRequest
import com.example.seonoh.seonohapp.util.CalculateUtils
import kotlinx.android.synthetic.main.coin_fragment.*


class CoinFragment : Fragment(), CoinRequest.BaseResult<ArrayList<CurrentPriceInfoModel>> {

    lateinit var mAdapter: CoinAdapter
    private var marketName: String? = null
    private val coinRepository by lazy { CoinRepositoryImpl() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        marketName = arguments?.getString(MARKET)
        coinRepository.sendCurrentPriceInfo(this,marketName!!)

        return inflater.inflate(R.layout.coin_fragment, container, false)
    }

    private fun setData(data: ArrayList<UseCoinModel>) {
        mAdapter.addCoinData(data)
    }

    private fun initView() {
        mAdapter = CoinAdapter()
        krwRecyclerView.apply {
            adapter = mAdapter
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

    }

    override fun getNetworkSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        var marketType = ""

        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        if (result.size != 0) {
            marketType = result[0].market.substringBefore("-")
        }

        val data = result.map {
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
        private const val MARKET = "market"

        fun newInstance(data: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString(MARKET, data)
            fragment.arguments = bundle
            return fragment
        }
    }
}
