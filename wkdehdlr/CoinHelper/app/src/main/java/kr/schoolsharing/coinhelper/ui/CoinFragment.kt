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
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.util.TextEditor

class CoinFragment : Fragment() {

    private val rVAdapter = MainRvAdapter()

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
                val marketList = markets
                    .filter { TextEditor.splitString(it.market, 0) == marketName }
                    .map { it.market }
                    .joinToString(",")
                loadUpbitTicker(marketList)
            }

            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun loadUpbitTicker(marketList: String) {
        Repository.getTicker(marketList, object : UpbitDataSource.GetTickerCallback {

            override fun onTickerLoaded(tickers: List<UpbitTicker>) {
                val tickerList: MutableList<UpbitItem> = ArrayList()
                tickers.forEach {
                    val data = UpbitItem(
                        TextEditor.splitString(it.market, 1),
                        TextEditor.makeTradePrice(it.tradePrice),
                        it.change,
                        TextEditor.makeSignedChangeRate(it.signedChangePrice),
                        TextEditor.makeAccTradePrice24h(it.accTradePrice24h)
                    )
                    tickerList.add(data)
                }

                rVAdapter.setTickerList(tickerList)
            }


            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}
