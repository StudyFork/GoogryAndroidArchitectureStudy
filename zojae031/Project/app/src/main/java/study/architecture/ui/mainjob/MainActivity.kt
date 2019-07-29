package study.architecture.ui.mainjob

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import study.architecture.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title = resources.getString(R.string.app_title)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this@MainActivity).inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        AlertDialog.Builder(this@MainActivity).apply {
            setTitle("코인헬퍼 클론 프로젝트")
            setMessage("Class03/Zojae031")
        }.show()
        return true
    }

    private fun pagerSetting() {
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        pager.adapter = MainPageAdapter(supportFragmentManager)
    }
}
