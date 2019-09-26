package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.base.BaseActivity
import kr.schoolsharing.coinhelper.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adaptPager()
    }

    private fun adaptPager() {
        with(PagerAdapter(supportFragmentManager)) {
            binding.viewpagerMain.adapter = this
            binding.tabsMain.setupWithViewPager(binding.viewpagerMain)
        }
    }

}
