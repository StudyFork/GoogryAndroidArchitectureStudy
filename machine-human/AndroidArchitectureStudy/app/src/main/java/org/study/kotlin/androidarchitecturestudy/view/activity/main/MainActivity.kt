package org.study.kotlin.androidarchitecturestudy.view.activity.main


import kotlinx.android.synthetic.main.activity_main.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter.MainViewPagerAdapter
import org.study.kotlin.androidarchitecturestudy.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onResume() {
        super.onResume()
        setupViewPager()
    }

    private fun setupViewPager() {

        val adapter = MainViewPagerAdapter(
            supportFragmentManager
        )

        listOf("KRW", "BTC", "ETH", "USDT").forEach {
            adapter.addFragment(MainFragment.createInstance(it), it)
        }

        viewpager_main.adapter = adapter
        tablayout_main.setupWithViewPager(viewpager_main)
    }


}

