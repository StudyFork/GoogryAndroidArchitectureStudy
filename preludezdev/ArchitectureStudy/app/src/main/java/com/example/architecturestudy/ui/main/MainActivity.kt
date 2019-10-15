package com.example.architecturestudy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseActivity
import com.example.architecturestudy.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vpAdapter = VpAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
        initClickEvent()
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.adapter = vpAdapter
            viewPager.offscreenPageLimit = vpAdapter.count
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    private fun initClickEvent() {
        binding.ivReload.setOnClickListener {
            Toast.makeText(this, "${binding.viewPager.currentItem}", Toast.LENGTH_SHORT).show()

            vpAdapter.getFragment(binding.viewPager.currentItem).refresh()
        }

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                vpAdapter.getFragment(position).refresh()
            }
        })
    }
}
