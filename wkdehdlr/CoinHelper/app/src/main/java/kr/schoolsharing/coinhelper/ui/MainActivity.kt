package kr.schoolsharing.coinhelper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.schoolsharing.coinhelper.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Repository().apply {
//            this.getTickerFromApi({
//                this.apiConnector.setUpbitMarket()
//
//                MyPagerAdapter(supportFragmentManager, this.apiConnector).apply {
//                    viewpager_main.adapter = this
//                    tabs_main.setupWithViewPager(viewpager_main)
//                }
//            }, {
//                Log.e("error :: ", it.message)
//            })
//        }
    }
}
