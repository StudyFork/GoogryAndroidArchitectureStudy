package study.architecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabSetting()
        pagerSetting()

    }

    private fun tabSetting() {
        with(tabLayout) {
            tabGravity = TabLayout.GRAVITY_FILL
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    pager.currentItem = tab!!.position
                }
            })
        }
    }

    private fun pagerSetting() {
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        pager.adapter = MainPageAdapter(supportFragmentManager)

    }


    inner class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = TAB_NUMBER


        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return MainFragment(MainFragment.TAB.KRW)
                1 -> return MainFragment(MainFragment.TAB.BTC)
                2 -> return MainFragment(MainFragment.TAB.ETH)
                3 -> return MainFragment(MainFragment.TAB.USDT)
            }
            return MainFragment(MainFragment.TAB.KRW)
        }

    }

    companion object {
        private const val TAB_NUMBER = 4
    }


}
