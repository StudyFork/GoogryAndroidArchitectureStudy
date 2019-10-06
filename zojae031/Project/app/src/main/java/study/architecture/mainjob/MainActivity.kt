package study.architecture.mainjob

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import study.architecture.R
import study.architecture.base.BaseActivity
import study.architecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            pager.adapter = MainPageAdapter(supportFragmentManager)
            viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
            lifecycleOwner = this@MainActivity
        }
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title = resources.getString(R.string.app_title)
        settingTab()
        settingPager()
    }

    private fun settingTab() {
        with(binding.tabLayout) {
            tabGravity = TabLayout.GRAVITY_FILL
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.pager.currentItem = tab?.position ?: 0
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

    private fun settingPager() {
        binding.pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
    }
}
