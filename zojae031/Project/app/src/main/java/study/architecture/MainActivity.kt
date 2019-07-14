package study.architecture

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
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
            tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_FILL
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
    }
}
