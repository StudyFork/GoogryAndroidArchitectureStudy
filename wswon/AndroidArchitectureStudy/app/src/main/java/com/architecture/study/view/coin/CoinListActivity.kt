package com.architecture.study.view.coin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.architecture.study.R
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.databinding.ActivityMainBinding
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.util.Injection
import com.architecture.study.viewmodel.CoinListViewModel
import com.google.android.material.tabs.TabLayout


class CoinListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val coinListViewModel by lazy {
        CoinListViewModel(
            CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource()),
            tabList.map { getString(it) }
        )
    }

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        coinListViewModel.run {
            if (isConnectedApi) {
                getMarketList {
                    Toast.LENGTH_SHORT
                    if (it == CoinListViewModel.SUCCESS) {
                        coinListViewModel.marketList.get()?.let { marketList ->
                            setTabPager(marketList)
                        }
                    } else {
                        showMessage(it)
                    }
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@CoinListActivity, message, Toast.LENGTH_SHORT).show()
    }

    /* tab layout && view pager init*/
    private fun setTabPager(marketList: List<MarketResponse>) {
        binding.tabLayoutMonetaryUnit.setupWithViewPager(binding.viewPagerCoinList)

        binding.viewPagerCoinList.run {
            adapter = object : FragmentPagerAdapter(
                supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {

                override fun getItem(position: Int): Fragment {
                    val monetaryUnitNameList = marketList
                        .filter { it.market.split("-")[0] == context.getString(tabList[position]) }
                        .map { it.market } as ArrayList<String>
                    return CoinListFragment.newInstance(monetaryUnitNameList)
                }

                override fun getCount(): Int =
                    tabList.size

                override fun getPageTitle(position: Int): CharSequence? =
                    getString(tabList[position])
            }
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutMonetaryUnit))
        }
    }
}
