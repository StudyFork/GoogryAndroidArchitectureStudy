package com.example.architecturestudy.ui.main

import android.os.Bundle
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseActivity
import com.example.architecturestudy.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vpAdapter = VpAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.adapter = vpAdapter
            viewPager.offscreenPageLimit = vpAdapter.count
            tabLayout.setupWithViewPager(viewPager)
        }
    }

}
