package com.example.mystudy.viewmodel

import androidx.databinding.ObservableField
import com.example.mystudy.adapter.ViewPagerAdapter

class MainViewModel(
    private val viewPagerAdapter: ViewPagerAdapter
){
    val mainAdapter = ObservableField<ViewPagerAdapter>()
    fun initView(){
        mainAdapter.set(viewPagerAdapter)
    }
}