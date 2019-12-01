package wooooooak.com.studyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.PAGE_BLOG
import wooooooak.com.studyapp.common.constants.PAGE_IMAGE
import wooooooak.com.studyapp.common.constants.PAGE_KIN
import wooooooak.com.studyapp.common.constants.PAGE_MOVIE

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = MainViewPagerAdapter(this)
        view_pager.isUserInputEnabled = false


        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_movie -> view_pager.currentItem = PAGE_MOVIE
                R.id.navigation_blog -> view_pager.currentItem = PAGE_BLOG
                R.id.navigation_image -> view_pager.currentItem = PAGE_IMAGE
                R.id.navigation_kin -> view_pager.currentItem = PAGE_KIN
            }
            true
        }

    }
}
