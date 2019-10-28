package com.example.seonoh.seonohapp

import android.os.Bundle
import com.example.seonoh.seonohapp.databinding.ActivityMainBinding
import com.example.seonoh.seonohapp.model.MainViewModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val pagerAdapter by lazy { TabPagerAdapter(supportFragmentManager) }
    override val viewModel = MainViewModel(CoinRepositoryImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            mainViewModel = viewModel
            coinViewPager.run {
                adapter = pagerAdapter
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            }
            tabLayout.run {
                addTab(tabLayout.newTab().setText("KRW"))
                addTab(tabLayout.newTab().setText("BTC"))
                addTab(tabLayout.newTab().setText("ETH"))
                addTab(tabLayout.newTab().setText("USDT"))
                addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))
            }
        }
    }

}
