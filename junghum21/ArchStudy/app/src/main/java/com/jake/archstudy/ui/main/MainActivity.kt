package com.jake.archstudy.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jake.archstudy.R
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRemoteDataSource
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.ActivityMainBinding
import com.jake.archstudy.ext.toast
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.ui.TickersFragment

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private val repository =
        UpbitRepository.getInstance(UpbitRemoteDataSource(ApiUtil.getUpbitService()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initTabLayout()
        getMarketAll()
    }

    private fun getMarketAll() {
        repository.getMarketAll(
            { response ->
                val markets = response.asSequence()
                    .groupBy { it.market.substringBefore("-") }
                    .map { map ->
                        val title = map.key
                        val markets = map.value.joinToString { it.market }
                        Market(title, markets)
                    }
                initViewPager(markets)
            },
            {
                toast(getString(R.string.fail_network))
            }
        )
    }

    private fun initTabLayout() {
        binding.tlMarket.run {
            val viewPager = binding.vpContent
            setupWithViewPager(viewPager)
        }
    }

    private fun initViewPager(markets: List<Market>) {
        binding.vpContent.run {
            adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
                override fun getItem(position: Int): Fragment {
                    return TickersFragment.newInstance(markets[position].markets)
                }

                override fun getCount() = markets.size

                override fun getPageTitle(position: Int): CharSequence? {
                    return markets[position].title
                }
            }
        }
    }

}