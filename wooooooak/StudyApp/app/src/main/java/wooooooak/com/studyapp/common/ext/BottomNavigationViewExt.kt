package wooooooak.com.studyapp.common.ext

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.PAGE_BLOG
import wooooooak.com.studyapp.common.constants.PAGE_IMAGE
import wooooooak.com.studyapp.common.constants.PAGE_KIN
import wooooooak.com.studyapp.common.constants.PAGE_MOVIE

@BindingAdapter("viewPager")
fun BottomNavigationView.setPageAdapter(viewPager: ViewPager2) {
    setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_movie -> viewPager.setCurrentItem(PAGE_MOVIE, false)
            R.id.navigation_blog -> viewPager.setCurrentItem(PAGE_BLOG, false)
            R.id.navigation_image -> viewPager.setCurrentItem(PAGE_IMAGE, false)
            R.id.navigation_kin -> viewPager.setCurrentItem(PAGE_KIN, false)
        }
        true
    }
}