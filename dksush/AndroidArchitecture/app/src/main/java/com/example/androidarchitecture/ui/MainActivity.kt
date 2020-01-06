package com.example.androidarchitecture.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityMainBinding
import com.example.androidarchitecture.ui.blog.BlogFragment
import com.example.androidarchitecture.ui.image.ImageFragment
import com.example.androidarchitecture.ui.kin.KinFragment
import com.example.androidarchitecture.ui.movie.MovieFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 반드시 탭보다 먼저 선언.
        binding.pager.adapter = ViewPagerAdapter(this)

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(
            binding.tabLayout,
            binding.pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "Movie"
                    1 -> "Image"
                    2 -> "Blog"
                    3 -> "Kin"
                    else -> ""
                }
            }).attach()
    }


    class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val fragmentList =
            listOf(MovieFragment(), ImageFragment(), BlogFragment(), KinFragment())

        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position] as Fragment
    }

}
