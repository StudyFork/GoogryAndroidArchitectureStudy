package com.example.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.architecturestudy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //각 마켓별 코인리스트
    companion object {
        var KRW_MARKETS: HashSet<String> = HashSet()
        var BTC_MARKETS: HashSet<String> = HashSet()
        var ETH_MARKETS: HashSet<String> = HashSet()
        var USDT_MARKETS: HashSet<String> = HashSet()
    }

    var vpAdapter = VpAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //뷰페이저 어댑터 연결
        viewPager.adapter = vpAdapter

        //탭 레이아웃에 뷰페이저 연결
        tabLayout.setupWithViewPager(viewPager)

        vpAdapter.notifyDataSetChanged()
    }
}
