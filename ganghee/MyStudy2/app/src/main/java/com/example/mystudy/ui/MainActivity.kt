package com.example.mystudy.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mystudy.R
import com.example.mystudy.adapter.ViewPagerAdapter
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.presenter.UpbitPresenter
import com.example.mystudy.ui.fragment.UpbitFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var upbitPresenter: UpbitPresenter
    private val upbitRepository = UpbitRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val upbitFragment = supportFragmentManager.findFragmentById(R.id.fragment_upbit)
                as UpbitFragment? ?: UpbitFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.fragment_upbit)
        }
        upbitPresenter = UpbitPresenter(upbitRepository, upbitFragment)

        configureMainTab()
    }

    private fun configureMainTab() {

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, num_fragment = 4)

        //TabLayout과 ViewPager를 연결한다!!
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.text = "KRW"
        tabLayout.getTabAt(1)!!.text = "BTC"
        tabLayout.getTabAt(2)!!.text = "ETH"
        tabLayout.getTabAt(3)!!.text = "USDT"

    }
}
