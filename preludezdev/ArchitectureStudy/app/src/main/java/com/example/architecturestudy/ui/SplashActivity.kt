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

        moveToMainActivity() //메인액티비티로 이동
    }

    //전체 코인시장목록 불러와서 MainActivity에 있는 전역변수에 저장하고 이동
    private fun moveToMainActivity() {
        NetworkHelper
            .coinApiService
            .getAllCoinMarket()
            .enqueue(object : Callback<List<CoinMarketResponse>> {
                override fun onResponse(
                    call: Call<List<CoinMarketResponse>>,
                    response: Response<List<CoinMarketResponse>>
                ) {
                    if (response.isSuccessful) {
                        val list: List<CoinMarketResponse>? = response.body()

                        if (list != null) {
                            for (num in 0 until list.size) {
                                val currMarketName = list[num].market
                                val arrMarketName = currMarketName.split("-")

                                when (arrMarketName[0]) {
                                    "KRW" -> MainActivity.KRW_MARKETS.add(currMarketName.trim())
                                    "BTC" -> MainActivity.BTC_MARKETS.add(currMarketName.trim())
                                    "ETH" -> MainActivity.ETH_MARKETS.add(currMarketName.trim())
                                    "USDT" -> MainActivity.USDT_MARKETS.add(currMarketName.trim())
                                }
                            }
                        }

                        // 메인엑티비티로 이동
                        Handler().postDelayed({
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        }, delayTime)
                    }
                }

                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}
