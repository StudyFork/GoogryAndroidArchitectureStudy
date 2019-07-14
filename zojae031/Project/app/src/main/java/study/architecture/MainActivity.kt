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

    /**
     * [FragmentPagerAdapter] vs [FragmentStatePagerAdapter]
     * @see FragmentPagerAdapter
     * 등록한 Fragment를 최대한 영구적으로 가지도록 구현되어있음
     * 적은양의 Fragment를 사용할 때 효과적
     * @see FragmentStatePagerAdapter
     * Fragment를 사용하지 않을때 Destroy가 됨
     * 많은양의 Fragment를 사용할 때 효과적
     * -> [FragemntPagerAdapter]를 사용한 이유 : 정해진 갯수의 프래그먼트를 사용하며, Destroy가 되는경우 API 콜이 다시 일어나기 때문에 오버헤드를 줄이기 위함
     */
    inner class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = TAB_NUMBER


        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return MainFragment(Companion.TAB.KRW)
                1 -> return MainFragment(Companion.TAB.BTC)
                2 -> return MainFragment(Companion.TAB.ETH)
                3 -> return MainFragment(Companion.TAB.USDT)
            }
            return MainFragment(Companion.TAB.KRW)
        }

    }

    companion object {
        private const val TAB_NUMBER = 4

        enum class TAB {
            KRW, BTC, ETH, USDT
        }
    }


}
