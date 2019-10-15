package com.architecture.study.view.coin

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.architecture.study.R
import com.architecture.study.base.BaseActivity
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.databinding.ActivityCoinBinding
import com.architecture.study.util.Injection
import com.architecture.study.viewmodel.MarketViewModel
import com.architecture.study.viewmodel.factory.MarketViewModelFactory
import com.google.android.material.tabs.TabLayout


class CoinListActivity : BaseActivity<ActivityCoinBinding>(R.layout.activity_coin) {

    private val marketViewModel by lazy {
        ViewModelProviders.of(this, MarketViewModelFactory()).get(MarketViewModel::class.java)
    }

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTabPager()
        marketViewModel.run {
            getMarketList()

            exceptionMessage.observe(this@CoinListActivity, Observer {
                showMessage(it)
            })

            marketList.observe(this@CoinListActivity, Observer { marketList ->
                supportFragmentManager.fragments.forEachIndexed { index, fragment ->
                    (fragment as? CoinListFragment)?.setMonetaryUnitList(marketList
                        .asSequence()
                        .filter {
                            it.market.split("-")[0] == getString(
                                tabList[index]
                            )
                        }
                        .map { it.market }
                        .toList()
                    )
                }
            })
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@CoinListActivity, message, Toast.LENGTH_SHORT).show()
    }

    /* tab layout && view pager init*/
    private fun setTabPager() {

        binding.run {
            tabLayoutMonetaryUnit.setupWithViewPager(viewPagerCoinList)

            viewPagerCoinList.run {
                offscreenPageLimit = 3
                adapter = object : FragmentPagerAdapter(
                    supportFragmentManager,
                    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
                ) {

                    override fun getItem(position: Int): Fragment =
                        CoinListFragment.newInstance()


                    override fun getCount(): Int =
                        tabList.size

                    override fun getPageTitle(position: Int): CharSequence? =
                        getString(tabList[position])
                }
                addOnPageChangeListener(
                    TabLayout.TabLayoutOnPageChangeListener(
                        tabLayoutMonetaryUnit
                    )
                )
            }
        }
    }
}
