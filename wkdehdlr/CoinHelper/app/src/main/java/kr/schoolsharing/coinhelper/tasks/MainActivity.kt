package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.schoolsharing.coinhelper.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adaptPager()
    }

    private fun adaptPager() {
        PagerAdapter(supportFragmentManager).apply {
            viewpager_main.adapter = this
            tabs_main.setupWithViewPager(viewpager_main)
        }
    }

}
