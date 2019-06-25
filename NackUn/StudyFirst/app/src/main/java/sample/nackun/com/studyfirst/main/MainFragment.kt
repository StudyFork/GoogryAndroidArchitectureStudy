package sample.nackun.com.studyfirst.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.main_fragment.view.*
import sample.nackun.com.studyfirst.Base.BaseFragment
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.TickerAdapter
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.vo.Ticker

class MainFragment : BaseFragment(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter
    private val tickerAdapter = TickerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        MainPresenter(Repository(RemoteDataSource()), this)
        with(root) {
            val onClickListener = object : View.OnClickListener {
                override fun onClick(v: View?) {
                    marketKRW.setTextColor(ContextCompat.getColor(context, R.color.grey))
                    marketBTC.setTextColor(ContextCompat.getColor(context, R.color.grey))
                    marketETH.setTextColor(ContextCompat.getColor(context, R.color.grey))
                    marketUSDT.setTextColor(ContextCompat.getColor(context, R.color.grey))
                    val selectedMarket = v as TextView
                    selectedMarket.setTextColor(ContextCompat.getColor(context, R.color.indigo))
                    presenter.requestTickers(selectedMarket.text.toString())
                }
            }
            marketKRW.setOnClickListener(onClickListener)
            marketBTC.setOnClickListener(onClickListener)
            marketETH.setOnClickListener(onClickListener)
            marketUSDT.setOnClickListener(onClickListener)

            tickerRecyclerView.adapter = tickerAdapter

            marketKRW.callOnClick()
        }

        return root
    }

    override fun showTickers(tickers: ArrayList<Ticker>) {
        tickerAdapter.setItems(tickers)
    }

    override fun showMsg(msg: String) {
        showToast(msg)
    }
}