package ado.sabgil.studyproject

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandler
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var upbitApiHandler: UpbitApiHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        upbitApiHandler = UpbitApiHandlerImpl.getInstance()

        upbitApiHandler.getAllTickers(UpbitTickerListRequest.Base.KRW,
            { result ->
                val tickerList: MutableList<Ticker> = mutableListOf()
                for (tickerResponse in result) {
                    tickerList.add(Ticker.from(tickerResponse))
                }
                Log.d("테스트", tickerList.toString())
            },
            { error -> Log.e("테스트", error.message) })
    }
}
