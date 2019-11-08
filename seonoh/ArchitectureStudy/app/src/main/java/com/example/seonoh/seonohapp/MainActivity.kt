package com.example.seonoh.seonohapp

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.seonoh.seonohapp.databinding.ActivityMainBinding
import com.example.seonoh.seonohapp.model.MainViewModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val pagerAdapter by lazy { TabPagerAdapter(supportFragmentManager) }

    @Suppress("UNCHECKED_CAST")
    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(itemClass: Class<T>): T {
                return MainViewModel(
                    CoinRepositoryImpl()
                ) as T
            }
        }).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            mainViewModel = viewModel
            coinViewPager.adapter = pagerAdapter
            tabLayout.setupWithViewPager(coinViewPager)
        }
    }

}
