package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.CoinListPagerAdapter
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import ado.sabgil.studyproject.enums.BaseCurrency
import ado.sabgil.studyproject.view.base.BaseActivity
import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import android.os.Bundle

class HomeActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vpCoinList.apply {

            adapter = CoinListPagerAdapter(
                supportFragmentManager,
                BaseCurrency.values().map {
                    it.toString() to CoinListFragment.newInstance(it)
                }.toMap()
            )

            offscreenPageLimit = BaseCurrency.values().size - 1
        }

        binding.tlCoinList.setupWithViewPager(binding.vpCoinList)
    }
}
