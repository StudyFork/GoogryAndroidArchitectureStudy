package ado.sabgil.studyproject

import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandler
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var upbitApiHandler: UpbitApiHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvTickerList.adapter = TickerAdapter()

        upbitApiHandler = UpbitApiHandlerImpl.getInstance()

        upbitApiHandler.getAllTickers(UpbitTickerListRequest.Base.KRW,
            { result ->
                val tickerList: MutableList<Ticker> = mutableListOf()
                for (tickerResponse in result) {
                    tickerList.add(Ticker.from(tickerResponse))
                }
                binding.it = tickerList
            },
            { error ->
            })
    }
}
