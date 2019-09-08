package com.android.studyfork.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.studyfork.R
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.adapter.ViewPagerAdapter
import com.android.studyfork.ui.main.presenter.MainContract
import com.android.studyfork.ui.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(supportFragmentManager) }
    private val presenter: MainPresenter by lazy { MainPresenter(UpbitRepositoryImpl, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        loadData()
    }

    override fun initViewPager() {
        with(pager_content) {
            adapter = viewPagerAdapter
            layout_tab.setupWithViewPager(this)
            offscreenPageLimit = 3
        }
    }

    private fun loadData() {
        presenter.loadData()
    }

    override fun setViewPagerTitle(titles: Array<String>) {
        viewPagerAdapter.setTitles(titles)
    }

    override fun setViewPagerData(items: Array<String>) {
        viewPagerAdapter.setData(items)
    }


}

