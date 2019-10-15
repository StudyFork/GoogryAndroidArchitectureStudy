package com.test.androidarchitecture.ui.market

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.base.BaseActivity
import com.test.androidarchitecture.databinding.ActivityMarketBinding

class MarketActivity
    : BaseActivity<ActivityMarketBinding, MarketViewModel>(R.layout.activity_market){

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override val vm = MarketViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(binding.mainViewPager) {
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
        }
        vm.getMarketAll()
        setObservableCallBack()
    }

    private fun setObservableCallBack(){
        vm.marketTitle.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.marketTitle.get()?.let { viewPagerAdapter.setData(it) }
            }
        })
        vm.toastMessage.addOnPropertyChangedCallback(object  : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MarketActivity, vm.toastMessage.get(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}