package com.example.architecturestudy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.network.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private val DELAY_TIME = 1300L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //코인시장목록 불러오기
        loadCoinMarketList()

        //메인액티비티로 이동
        redirectByTime()
    }

    private fun redirectByTime() {
        Handler().postDelayed({ startActivity(Intent(this@SplashActivity, MainActivity::class.java)) }, DELAY_TIME)
    }


    //업비트api에서 전체 마켓목록 가져오기
    fun loadCoinMarketList() {
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
                            arrMarketName.contains("KRW") -> MainActivity.KRWList.add(currMarketName.trim())
                            arrMarketName.contains("BTC") -> MainActivity.BTCList.add(currMarketName.trim())
                            arrMarketName.contains("ETH") -> MainActivity.ETHList.add(currMarketName.trim())
                            arrMarketName.contains("USDT") -> MainActivity.USDTList.add(currMarketName.trim())
                        }
                    }

                    for (str in MainActivity.KRWList) {
                        sb.append(str + "\n")
                    }
                }

                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}
