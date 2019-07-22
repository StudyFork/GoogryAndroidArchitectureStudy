package kr.schoolsharing.coinhelper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.network.ApiConnector
import kr.schoolsharing.coinhelper.util.MyPagerAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiConnector = ApiConnector()
        apiConnector.setUpbitMarket()

        val myPagerAdapter = MyPagerAdapter(supportFragmentManager, apiConnector)
        viewpager_main.adapter = myPagerAdapter
        tabs_main.setupWithViewPager(viewpager_main)
    }
}
