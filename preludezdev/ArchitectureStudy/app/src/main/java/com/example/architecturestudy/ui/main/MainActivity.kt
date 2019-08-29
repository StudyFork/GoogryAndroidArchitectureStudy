package com.example.architecturestudy.ui.main

import android.os.Bundle
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseActivity
import com.example.architecturestudy.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val vpAdapter = VpAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        initViewPager()
        initArrowView()
    }

    private fun initViewPager() {
        //뷰페이저 어댑터 연결
        binding.viewPager.adapter = vpAdapter

        //탭 레이아웃에 뷰페이저 연결
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun initArrowView() {
        //default sort 값, 임시로 해둠...
        binding.ivArrowCoinName.isSelected = true
    }

}
