package sample.nackun.com.studyfirst.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_recyclerview.view.*
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.TickerAdapter
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

class MainFragment : Fragment(), MainContract.View{
    override var marketName: String= "BTC"
    override lateinit var presenter: MainContract.Presenter
    private val tickerAdapter = TickerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.main_recyclerview, container, false)

        with(root){
            tickerRecyclerView.adapter =  tickerAdapter
            //val recyclerView = findViewById<RecyclerView>(R.id.tickerRecyclerView).let { it.adapter =  tickerAdapter}
        }
        val mainPresenter = MainPresenter(Repository(RemoteDataSource), this)
        mainPresenter.start()
        mainPresenter.arr.let {
            tickerAdapter.setItems(it)
        }
        return root
    }

    fun selectedMarket(): String{
        return "BTC"
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}