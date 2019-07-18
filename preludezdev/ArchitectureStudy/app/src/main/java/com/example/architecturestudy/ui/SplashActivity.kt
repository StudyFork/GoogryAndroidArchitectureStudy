package com.example.architecturestudy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.network.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private val delayTime = 1300L //스플래시 화면에 머무는 시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadCoinMarketList() //메인액티비티로 이동

        redirectByTime() //전체 코인시장목록 불러오기
    }

    //메인액티비티로 이동
    private fun redirectByTime() {
        Handler().postDelayed(
            { startActivity(Intent(this@SplashActivity, MainActivity::class.java)) },
            delayTime
        )
    }

    //전체 코인시장목록 불러와서 MainActivity에 있는 전역변수에 저장
    private fun loadCoinMarketList() {
        NetworkHelper
            .coinApiService
            .getAllCoinMarket()
            .enqueue(object : Callback<List<CoinMarketResponse>> {
                override fun onResponse(
                    call: Call<List<CoinMarketResponse>>,
                    response: Response<List<CoinMarketResponse>>
                ) {
                    var list: List<CoinMarketResponse>? = response.body()

                    var sb = StringBuffer()

                    for (num in 0 until list!!.size - 1) {
                        var currMarketName = list[num].market
                        var arrMarketName = currMarketName.split("-")

                        when {
                            arrMarketName.contains("KRW") -> MainActivity.KRW_MARKETS.add(currMarketName.trim())
                            arrMarketName.contains("BTC") -> MainActivity.BTC_MARKETS.add(currMarketName.trim())
                            arrMarketName.contains("ETH") -> MainActivity.ETH_MARKETS.add(currMarketName.trim())
                            arrMarketName.contains("USDT") -> MainActivity.USDT_MARKETS.add(currMarketName.trim())
                        }
                    }

                    for (str in MainActivity.KRW_MARKETS) {
                        sb.append(str + "\n")
                    }
                }

                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}
