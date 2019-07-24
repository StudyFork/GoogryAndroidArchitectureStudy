package com.example.mystudy.activity

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

        //TabLayout과 ViewPager를 연결한다!!
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.text = "KRW"
        tabLayout.getTabAt(1)!!.text = "BTC"
        tabLayout.getTabAt(2)!!.text = "ETH"
        tabLayout.getTabAt(3)!!.text = "USDT"

    }
}
