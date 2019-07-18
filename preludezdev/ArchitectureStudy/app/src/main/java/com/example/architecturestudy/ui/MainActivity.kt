package com.example.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.network.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_btc.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        var KRWList: HashSet<String> = HashSet()
        var BTCList: HashSet<String> = HashSet()
        var ETHList: HashSet<String> = HashSet()
        var USDTList: HashSet<String> = HashSet()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //뷰페이저 어댑터 연결
        viewPager.adapter = MainAdapter(supportFragmentManager)

        //탭 레이아웃에 뷰페이저 연결
        tabLayout.setupWithViewPager(viewPager)

        //코인시장목록 불러오기
        loadCoinMarketList()
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

                    for (num in 0..list!!.size - 1) {
                        var currMarketName = list[num].market
                        var arrMarketName = currMarketName.split("-")


                        when {
                            arrMarketName.contains("KRW") -> KRWList.add(currMarketName)
                            arrMarketName.contains("BTC") -> BTCList.add(currMarketName)
                            arrMarketName.contains("ETH") -> ETHList.add(currMarketName)
                            arrMarketName.contains("USDT") -> USDTList.add(currMarketName)
                        }
                    }

                    for (str in KRWList) {
                        sb.append(str + "\n")
                    }

                    textViewBTC.text = sb.toString()
                }

                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    textViewBTC.text = t.toString()
                }
            })
    }
}
