package com.example.mystudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mystudy.R
import com.example.mystudy.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        configureMainTab()
    }

    private fun configureMainTab() {

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, num_fragment = 4)

        with(tabLayout){
            setupWithViewPager(viewPager)
            getTabAt(0)?.text = "KRW"
            getTabAt(1)?.text = "BTC"
            getTabAt(2)?.text = "ETH"
            getTabAt(3)?.text = "USDT"
        }
    }
}
