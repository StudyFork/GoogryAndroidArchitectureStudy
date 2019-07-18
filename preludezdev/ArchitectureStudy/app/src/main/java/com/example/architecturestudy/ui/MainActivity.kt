package com.example.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import com.example.architecturestudy.data.CoinMarketResponse
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


    }
}
