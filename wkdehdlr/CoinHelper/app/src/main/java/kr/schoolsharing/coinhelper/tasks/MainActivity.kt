package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adaptPager()
    }

    private fun adaptPager() {
        PagerAdapter(supportFragmentManager).apply {
            binding.viewpagerMain.adapter = this
            binding.tabsMain.setupWithViewPager(binding.viewpagerMain)
        }
    }

}
