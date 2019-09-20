package com.example.mystudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mystudy.R
import com.example.mystudy.adapter.ViewPagerAdapter
import com.example.mystudy.databinding.ActivityMainBinding
import com.example.mystudy.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
 
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        MainViewModel(ViewPagerAdapter(supportFragmentManager)).run{
            binding.vm = this
            initView()
        }
        binding.tabLayout.run {
            setupWithViewPager(binding.viewPager)
        }
    }
}
