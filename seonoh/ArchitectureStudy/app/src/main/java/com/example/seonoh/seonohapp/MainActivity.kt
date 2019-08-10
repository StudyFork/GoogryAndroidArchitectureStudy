package com.example.seonoh.seonohapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mPagerAdapter : TabPagerAdapter
    private val TAG = "COIN_MAIN"
    private val TAB_COUNT = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPagerAdapter = TabPagerAdapter(supportFragmentManager, TAB_COUNT)
        coinViewPager.adapter = mPagerAdapter


        coinViewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        Log.e(TAG,"KRW")
                    }

                    1 -> {
                        Log.e(TAG,"BTC")

                    }

                    2 -> {
                        Log.e(TAG,"ETH")

                    }
                    3 -> {
                        Log.e(TAG,"USDT")

                    }


                }

            }


        })

        tabLayout.apply {
            addTab(tabLayout.newTab().setText("KRW"))
            addTab(tabLayout.newTab().setText("BTC"))
            addTab(tabLayout.newTab().setText("ETH"))
            addTab(tabLayout.newTab().setText("USDT"))
        }

        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))



    }
}
