package com.example.architecturestudy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.data.source.CoinsRepository
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        moveToMainActivity() //메인액티비티로 이동
    }

    //전체 코인시장목록 불러와서 MainActivity에 있는 전역변수에 저장하고 이동
    private fun moveToMainActivity() {
        CoinsRepository
            .getInstance(CoinsRemoteDataSource, CoinsLocalDataSource)
            .getAllMarket(object : CoinsDataSource.GetAllMarketCallback {
                override fun onAllMarketLoaded(markets: List<CoinMarketResponse>) {
                    for (currMarket in markets) {
                        val currMarketName = currMarket.market

                        when (currMarketName.split('-')[0]) {
                            "KRW" -> MainActivity.KRW_MARKETS.add(currMarketName.trim())
                            "BTC" -> MainActivity.BTC_MARKETS.add(currMarketName.trim())
                            "ETH" -> MainActivity.ETH_MARKETS.add(currMarketName.trim())
                            "USDT" -> MainActivity.USDT_MARKETS.add(currMarketName.trim())
                        }
                    }

                    // 메인엑티비티로 이동
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))

                    finish() // 현재액티비티 종료
                }

                override fun onDataNotAvailable() {
                    Log.d("test", "All Market Data Not Available")
                }
            })

    }
}
