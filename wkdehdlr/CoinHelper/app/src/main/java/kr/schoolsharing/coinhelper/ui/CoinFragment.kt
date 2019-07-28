package kr.schoolsharing.coinhelper.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.data.UpbitDataSource
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.model.UpbitList
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.util.TextEditor

class CoinFragment() : Fragment() {

    private lateinit var marketList: String
    private val rVAdapter = MainRvAdapter(UpbitList.getListFromName(marketName))

    companion object {
        fun newInstance(marketName: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()

            bundle.putString("MARKET_NAME", marketName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coinrecycler, container, false)
        val coinRecyclerView = view.findViewById<RecyclerView>(R.id.CoinRecyclerView)

        coinRecyclerView.adapter = rVAdapter

        val lm = LinearLayoutManager(context!!)
        coinRecyclerView.layoutManager = lm
        coinRecyclerView.setHasFixedSize(true)


        val marketName = arguments?.get("MARKET_NAME") as String
        loadUpbitMarket(marketName)

        return view
    }


    private fun loadUpbitMarket(marketName: String) {
        Repository.getMarket(object : UpbitDataSource.GetMarketCallback {
            override fun onMarketLoaded(markets: List<UpbitMarket>) {
                marketList = markets.joinToString(",") { it.market }
                loadUpbitTicker(marketName)
            }

            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun loadUpbitTicker(marketName: String) {
        Repository.getTicker(marketList, object : UpbitDataSource.GetTickerCallback {

            override fun onTickerLoaded(tickers: List<UpbitTicker>) {
                for (item in tickers) {

                    val data = UpbitItem(
                        TextEditor.splitString(item.market, 1),
                        TextEditor.makeTradePrice(item.tradePrice),
                        item.change,
                        TextEditor.makeSignedChangeRate(item.signedChangePrice),
                        TextEditor.makeAccTradePrice24h(item.accTradePrice24h)
                    )

                    when (TextEditor.splitString(item.market, 0)) {
                        "KRW" -> UpbitList.krwList.add(data)
                        "BTC" -> UpbitList.btcList.add(data)
                        "ETH" -> UpbitList.ethList.add(data)
                        else -> UpbitList.usdtList.add(data)
                    }
                }
                rVAdapter.notifyDataSetChanged()
            }


            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}
