package com.example.architecturestudy.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseActivity
import com.example.architecturestudy.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val vpAdapter = VpAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {
        //뷰페이저 어댑터 연결
        binding.viewPager.adapter = vpAdapter

        //탭 레이아웃에 뷰페이저 연결
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        //default sort 값, 임시로 해둠...
        binding.ivArrowCoinName.isSelected = true
    }

}
