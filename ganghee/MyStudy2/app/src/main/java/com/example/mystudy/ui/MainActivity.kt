package com.example.mystudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mystudy.R
import com.example.mystudy.adapter.ViewPagerAdapter
import com.example.mystudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        configureMainTab()
    }

    private fun configureMainTab() {

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, num_fragment = 4)

        with(binding.tabLayout){
            setupWithViewPager(binding.viewPager)
            getTabAt(0)?.text = "KRW"
            getTabAt(1)?.text = "BTC"
            getTabAt(2)?.text = "ETH"
            getTabAt(3)?.text = "USDT"
        }
    }
}
