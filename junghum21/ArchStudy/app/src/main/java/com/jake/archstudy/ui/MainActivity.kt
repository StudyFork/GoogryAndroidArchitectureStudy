package com.jake.archstudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.jake.archstudy.R
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.ActivityMainBinding
import com.jake.archstudy.network.response.MarketResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val repository = UpbitRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initTabLayout()
        getMarketAll()
    }

    private fun getMarketAll() {
        repository.getMarketAll()
            .enqueue(object : Callback<List<MarketResponse>?> {
                override fun onFailure(call: Call<List<MarketResponse>?>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<MarketResponse>?>,
                    response: Response<List<MarketResponse>?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { body ->
                            val markets = body.groupBy { it.market.split("-")[0] }
                                .map { map ->
                                    val title = map.key
                                    val markets = map.value.joinToString(separator = ",") {
                                        it.market
                                    }
                                    Market(title, markets)
                                }
                            initViewPager(markets)
                        }
                    }
                }
            })
    }

    private fun initTabLayout() {
        binding.tlMarket.run {
            val viewPager = binding.vpContent
            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
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