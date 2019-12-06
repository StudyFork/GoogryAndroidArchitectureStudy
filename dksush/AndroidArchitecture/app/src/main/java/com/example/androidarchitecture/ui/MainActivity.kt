package com.example.androidarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidarchitecture.R
import com.example.androidarchitecture.fragment.BlogFragment
import com.example.androidarchitecture.fragment.ImageFragment
import com.example.androidarchitecture.fragment.KinFragment
import com.example.androidarchitecture.fragment.MovieFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 반드시 탭보다 먼저 선언.
        pager.adapter = ViewPagerAdapter(this)

        tab_layout.tabGravity =TabLayout.GRAVITY_FILL
        TabLayoutMediator(tab_layout, pager, TabLayoutMediator.TabConfigurationStrategy{tab, position ->
            when(position){
                0-> tab.text = "Movie"
                1-> tab.text = "Image"
                2-> tab.text = "Blog"
                3-> tab.text = "Kin"
            }
        }).attach()
    }


    class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val fragmentList = listOf(MovieFragment(), ImageFragment(), BlogFragment(), KinFragment())
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }


}
