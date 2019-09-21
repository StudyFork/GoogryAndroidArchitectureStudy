package com.test.androidarchitecture.ui.market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.data.MarketTitle
import kotlinx.android.synthetic.main.activity_market.*

class MarketActivity :
    AppCompatActivity(),
    MarketContract.View {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val presenter by lazy { MarketPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)
        main_tabLayout.setupWithViewPager(main_viewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(main_viewPager) {
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
        }
        presenter.getMarketAll()
    }

    override fun onDestroy() {
        presenter.disposablesClear()
        super.onDestroy()
    }

    override fun setViewpagerData(list: List<MarketTitle>) {
        viewPagerAdapter.setData(list)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}