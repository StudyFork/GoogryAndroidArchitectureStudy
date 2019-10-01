package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.main.MainContract
import com.jskim5923.architecturestudy.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val viewpagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager)
    }

    override val presenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewPager) {
            tabLayout.setupWithViewPager(this)
            adapter = viewpagerAdapter
        }

        presenter.loadMarketList()
    }

    override fun updateViewpager(marketList: List<String>) {
        viewpagerAdapter.updateItem(marketList)
    }

    override fun onDestroy() {
        presenter.clearCompositeDisposable()
        super.onDestroy()
    }
}
