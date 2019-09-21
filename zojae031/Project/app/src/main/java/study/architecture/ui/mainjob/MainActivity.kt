package study.architecture.ui.mainjob

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import study.architecture.R
import study.architecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(MainViewModel(MainPageAdapter(supportFragmentManager))) {
            binding.viewModel = this
            initView()
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
