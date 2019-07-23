package kr.schoolsharing.coinhelper.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.util.MyPagerAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Repository().apply {
            this.getTickerFromApi({
                this.apiConnector.setUpbitMarket()

                MyPagerAdapter(supportFragmentManager, this.apiConnector).apply {
                    viewpager_main.adapter = this
                    tabs_main.setupWithViewPager(viewpager_main)
                }
            }, {
                Log.e("error :: ", it.message)
            })
        }
    }
}
