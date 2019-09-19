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

        with(MainViewModel(ViewPagerAdapter(supportFragmentManager,4))){
            binding.vm = this
            initView()
        }
        configureMainTab()
    }

    private fun configureMainTab() {

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, num_fragment = 4)

    }
}
