package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.aiden.aiden.architecturepatternstudy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_vp.adapter = MainPagerAdapter(supportFragmentManager)
        main_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setSelectedTabColor(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        val movePageListener: View.OnClickListener = View.OnClickListener {
            setSelectedTabColor(it.tag as Int)
            main_vp.currentItem = it.tag as Int
        }

        main_market_tab_tv_krw.setOnClickListener(movePageListener)
        main_market_tab_tv_krw.tag = 0
        main_market_tab_tv_btc.setOnClickListener(movePageListener)
        main_market_tab_tv_btc.tag = 1
        main_market_tab_tv_eth.setOnClickListener(movePageListener)
        main_market_tab_tv_eth.tag = 2
        main_market_tab_tv_usdt.setOnClickListener(movePageListener)
        main_market_tab_tv_usdt.tag = 3
        setSelectedTabColor(0)
    }

    private fun setSelectedTabColor(position: Int) {
        initializeTabColor()
        when (position) {
            0 -> main_market_tab_tv_krw.setTextColor(getColor(R.color.accent))
            1 -> main_market_tab_tv_btc.setTextColor(getColor(R.color.accent))
            2 -> main_market_tab_tv_eth.setTextColor(getColor(R.color.accent))
            3 -> main_market_tab_tv_usdt.setTextColor(getColor(R.color.accent))
            else -> return
        }
    }

    private fun initializeTabColor() {
        main_market_tab_tv_krw.setTextColor(getColor(R.color.grey))
        main_market_tab_tv_btc.setTextColor(getColor(R.color.grey))
        main_market_tab_tv_eth.setTextColor(getColor(R.color.grey))
        main_market_tab_tv_usdt.setTextColor(getColor(R.color.grey))
    }

}
