package com.example.mystudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mystudy.R
import com.example.mystudy.adapter.ViewPagerAdapter
import com.example.mystudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewPagerAdapter by lazy { ViewPagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.run {
            viewPager.adapter = viewPagerAdapter
            tabLayout.setupWithViewPager(binding.viewPager)
        }
    }
}
 